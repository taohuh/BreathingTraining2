package com.taohuh.breathingtraining.fragment;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taohuh.breathingtraining.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class RecordBreathFragment extends Fragment {

    public interface FragmentListener {
        void onSaveResultRecordListener();
        void onCancelRecordListener();
    }

    private static final String TAG = "BT: RecordBreathFM";
    Button btnStartRecord, btnCancelRecord, btnRestartRecord;
    MediaRecorder recorder;
    RecordAmplitude recordAmplitude;
    boolean isRecording = false;
    File audioFile;

    int noOfAmp = 0;
    CountDownTimer cdt;

    public static int[] firstRecord = new int[50];
    int first = 0;

    double amp = 0;
    double count = 0.0;
    double bpm = 0.0;
    int position = 0;


    public RecordBreathFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static RecordBreathFragment newInstance() {
        RecordBreathFragment fragment = new RecordBreathFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_record_breath, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnStartRecord = (Button) rootView.findViewById(R.id.btnStartRecord);
        btnCancelRecord = (Button) rootView.findViewById(R.id.btnCancelRecord);
        btnRestartRecord = (Button) rootView.findViewById(R.id.btnRestartRecord);

        btnStartRecord.setOnClickListener(clickedListener);
        btnRestartRecord.setOnClickListener(clickedListener);
        btnCancelRecord.setOnClickListener(clickedListener);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    private class RecordAmplitude extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (isRecording) {
                //วนลูปเรื่อยๆตอนที่บันทึก ทุกครึ่งวินาทีจะส่งค่าไป onProgressUpdate
                //ส่งค่านี้ไป คือ MaxAmplitude/1000
                publishProgress(recorder.getMaxAmplitude() / 1000);
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                if (isCancelled()) break;
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

            //Show Amplitude
            //Show Number of Amplitude

            for (int i = 0; i < progress.length; i++) {
                amp = (double) progress[i];
                noOfAmp++;

                //อาเรย์เก็บค่าเสียงที่บันทึกไว้
                //จริงๆแล้ว progress[i] มันมีช่องเดียวคือ 0 แต่มันวนลูปทับเรื่อยๆ ไม่เพิ่มช่อง
                //เลยต้องย้ายมาใส่อาเรย์ใหม่ เคนะ

                firstRecord[first] = progress[i];
                //แสดงผลค่าที่บันทึกมาแต่ละช่อง
                //ดังนั้น ค่าที่พิมพ์ออกมาก็จะอยู่ในอาเรย์นี้ firstRecord[first] เอาอันนี้ไปใช้ได้
                Log.d(TAG, "onProgressUpdate() Record : " + firstRecord[first]);

                first++;
            }
            //แสดงผลค่าที่บันทึกได้จากอาเรย์ progress[0]
            Log.d(TAG, "onProgressUpdate() " + progress[0].toString());
            //amplitudeTextView.setText(progress[0].toString());
        }
    }

    /**
     * Listener Zone
     */
    final View.OnClickListener clickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnStartRecord) {
                recordBreath();
            } else if (v.getId() == R.id.btnRestartRecord) {
                restartRecordBreath();
            } else if (v.getId() == R.id.btnCancelRecord) {
                cancelRecordBreath();
            }
        }
    };

    /**
     * Inner Class
     */
    private void recordBreath() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        File path = new File(
                Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/Android/data/com.taohuh.breathingtraining/files/");
        path.mkdirs();
        try {
            audioFile = File.createTempFile("recording", ".3gp", path);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Couldn't create recording audio file", e);
        }
        recorder.setOutputFile(audioFile.getAbsolutePath());

        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            throw new RuntimeException(
                    "IllegalStateException on MediaRecorder.prepare", e);
        } catch (IOException e) {
            throw new RuntimeException(
                    "IOException on MediaRecorder.prepare", e);
        }

        recorder.start();
        isRecording = true;
        recordAmplitude = new RecordAmplitude();
        recordAmplitude.execute();

        //ตัวจับเวลา อันนี้จับ 15 วินาที แล้วทุกๆ ครึ่งวิจะนับเวลาถอยหลัง
        cdt = new CountDownTimer(15000, 50) {
            //อันนี้ไม่มีไร ไว้แสดงเวลาเฉยๆ
            @Override
            public void onTick(long l) {
                String strTime = String.format("%.1f"
                        , (double) l / 1000);
                //tvTimer.setText(String.valueOf(strTime));
                Log.d(TAG, "onTick() " + strTime);

            }

            //พอนับเวลาเสร็จจะทำอันนี้คือหยุดบันทึกเสียง
            @Override
            public void onFinish() {
                //tvTimer.setText("0");
                isRecording = false;
                recordAmplitude.cancel(true);
                recorder.stop();
                recorder.release();
                recorder = null;

                for (int i = 0; i < firstRecord.length; i++) {
                    if (position != (firstRecord.length) - 1) {
                        //ตัวแรกเป็น 0 แต่ตัวที่ 2,3 != 0
                        //กรณีหายใจครบ 1 ครั้ง
                        // 0120 = 1 ครั้ง
                        if (firstRecord[position] == 0 && firstRecord[position + 1] != 0
                                && firstRecord[position + 2] != 0) {
                            System.out.println("case1 match!!");
                            count = count + 1.0;
                            //System.out.println("Count: " + count);
                            position++;
                        } else {
                            System.out.println("Case1 Mismatch!!");
                            position++;
                        }
                        // ตัวแรกเป็น 0 แต่ตัวที่ 2 != 0
                        // กรณีหายใจครึ่งเดียว
                        if (position != (firstRecord.length) - 1) {
                            if (firstRecord[position] == 0 && firstRecord[position + 1] != 0) {
                                System.out.println("case2 match!!");
                                count = count + 0.5;
                                //System.out.println("Count: " + count);
                                position++;
                            } else {
                                System.out.println("Case2 Mismatch!!");
                                position++;
                            }
                        }
                    }
                }


                bpm = count * 4.0;
                Log.d(TAG, "onFinish() Count : " + count + " Bpm : " + bpm);
                first = 0;

                //stopRecording.setEnabled(false);
                //startRecording.setEnabled(true);

                //ส่งข้อมูลไปหน้า Show result
                FragmentListener listener = (FragmentListener)getActivity();
                listener.onSaveResultRecordListener();

                /*Intent intentRecord = new Intent(getApplicationContext(), Show_result_first_BPM_page.class);
                intentRecord.putExtra("Bpm", bpm);
                intentRecord.putExtra("name", name);
                intentRecord.putExtra("age", age);
                startActivity(intentRecord);*/
            }
        }.start();
    }

    private void cancelRecordBreath() {
        //btnStop
        //หยุดบันทึกเสียง ค่อยกลับไปหน้ารายชื่อ
        first = 0;
        isRecording = false;
        if(recordAmplitude != null){
            recordAmplitude.cancel(true);
        }

        if(recorder != null){
            recorder.stop();
            recorder.release();
            recorder = null;
        }

        if(cdt != null){
            cdt.cancel();
        }


        FragmentListener listener = (FragmentListener)getActivity();
        listener.onCancelRecordListener();
    }

    private void restartRecordBreath() {
        //หยุดบันทึกเสียง ค่อยกดปุ่มเริ่มใหม่
        first = 0;
        isRecording = false;
        if(recordAmplitude != null){
            recordAmplitude.cancel(true);
        }

        if(recorder != null){
            recorder.stop();
            recorder.release();
            recorder = null;
        }

        if(cdt != null){
            cdt.cancel();
        }

        //tvTimer.setText("0");

        //set Button
        //stopRecording.setEnabled(true);
        //restartRecording.setEnabled(false);
        //startRecording.setEnabled(true);
    }

}

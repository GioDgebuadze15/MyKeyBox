package com.example.mykeybox;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.mykeybox.adapters.GridViewAdapter;
//import com.example.mykeybox.databinding.ActivityMainBinding;
import com.example.mykeybox.models.DeviceInfoModel;
import com.example.mykeybox.retrofit.IDeviceInfo;
import com.example.mykeybox.retrofit.RetrofitInstance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding activityMainBinding;
    ArrayList<DeviceInfoModel> deviceInfoArrayList;
//    private MainActivityClickHandlers handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
//        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        handlers = new MainActivityClickHandlers();
//        activityMainBinding.setClickHandlers(handlers);

//        GridView gridView = findViewById(R.id.gridView);
//
//        GridViewAdapter gridViewAdapter = new GridViewAdapter(this);
//        gridView.setAdapter(gridViewAdapter);
//        //-------------------------------
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX


        // XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

//        btn1 = (Button) findViewById(R.id.button1);
//        btn2 = (Button) findViewById(R.id.button2);
//        btn3 = (Button) findViewById(R.id.button3);
//        btn4 = (Button) findViewById(R.id.button4);

        //-------------------------------

//        byte[] request1;
//        byte[] request2;
//        byte[] request3;
//        byte[] request4;
//
//        request1 = new byte[]{(byte) 0x02, (byte) 0x00, (byte) 0x31, (byte) 0x03, (byte) 0x36};   // open N1
//        request2 = new byte[]{(byte) 0x02, (byte) 0x01, (byte) 0x31, (byte) 0x03, (byte) 0x37};   // open N2
//        request3 = new byte[]{(byte) 0x02, (byte) 0x02, (byte) 0x31, (byte) 0x03, (byte) 0x38};   // open N3
//        request4 = new byte[]{(byte) 0x02, (byte) 0x03, (byte) 0x31, (byte) 0x03, (byte) 0x39};   // open N4
//
//        response = new byte[11];
//
//        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
//        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
//
//        if (availableDrivers.isEmpty()) {
//            return;
//        }
//
//        // Open a connection to the first available driver.
//        UsbSerialDriver driver = availableDrivers.get(0);
//        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
//        UsbSerialPort port = driver.getPorts().get(0); // Most devices have just one port (port 0)
//        try {
//            port.open(connection);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            port.setParameters(19200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //-------------------------------


//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    port.write(request1, 500);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                try {
//                    port.write(request2, 500);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    port.write(request3, 500);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    port.write(request4, 500);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "MYKEYBOX  info@mykeybox.com", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        content(port);


    }
    public void getData() {
        IDeviceInfo iDeviceInfo = RetrofitInstance.getInstance();
        Call<ArrayList<DeviceInfoModel>> call = iDeviceInfo.getResult();

        call.enqueue(new Callback<ArrayList<DeviceInfoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DeviceInfoModel>> call, Response<ArrayList<DeviceInfoModel>> response) {
                deviceInfoArrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    deviceInfoArrayList.addAll(response.body().subList(0,20));
                }

                for (DeviceInfoModel device : deviceInfoArrayList) {
                    Log.i("ANSWER", device.getId()+" "+device.getCompleted());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<DeviceInfoModel>> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });
    }
//    public class MainActivityClickHandlers{
//        public void onFABClicked(View view) {
//
//        }
//    }

//        byte[] request;
//        byte[] response;
//
//        request = new byte[]{(byte) 0x02, (byte) 0x00, (byte) 0x30, (byte) 0x03, (byte) 0x35};     get status
//        response = new byte[200];
//        try {
//            port.write(request,100);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            int read = port.read(response, 100);
//        } catch (IOException e) {
//            e.printStackTrace();
//            btn3.setText("exceprtion");
//        }
//        btn4.setText(response.toString());
//


    //int len = port.read(response, 100);

    // btn2.setText("refreshed :"+count + "  --len-- " );


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


//    String hexToBin(String hex) {
//        int i = Integer.parseInt(hex, 16);
//        Log.i("MI", String.valueOf(i));
//        Log.i("MII", Integer.toString(i, 2));
//
//
//        String bin = Integer.toBinaryString(i);
//        Log.i("M2",bin);
//        bin=leftRight(bin,4).replace(' ','0');
//        return bin;
//    }


    public static String leftRight(String s, int n) {
        return String.format("%04d", Integer.parseInt(s));
    }

    private void writeToFile(String message) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todolist.txt", Context.MODE_PRIVATE));
            try {
                outputStreamWriter.write(message);
                outputStreamWriter.close(); //always close your stream
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String readFromFile() throws IOException {
        String result = "";
        InputStream inputStream = openFileInput("todolist.txt");
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tempString = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((tempString = bufferedReader.readLine()) != null) {
                stringBuilder.append((tempString));
            }
            inputStream.close();
            result = stringBuilder.toString();
        }

        return result;
    }
}
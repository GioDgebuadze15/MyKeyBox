package com.example.mykeybox;


import android.content.Context;
import android.graphics.Color;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class Repository {
    private static final byte[] REQUEST_1 = new byte[]{(byte) 0x02, (byte) 0x00, (byte) 0x31, (byte) 0x03, (byte) 0x36};   // open N1
    private static final byte[] REQUEST_2 = new byte[]{(byte) 0x02, (byte) 0x01, (byte) 0x31, (byte) 0x03, (byte) 0x37};   // open N2
    private static final byte[] REQUEST_3 = new byte[]{(byte) 0x02, (byte) 0x02, (byte) 0x31, (byte) 0x03, (byte) 0x38};   // open N3
    private static final byte[] REQUEST_4 = new byte[]{(byte) 0x02, (byte) 0x03, (byte) 0x31, (byte) 0x03, (byte) 0x39};   // open N4

    private byte[] response = new byte[11];

    private int counter;

    Context mContext;
    Button btn;

    private UsbManager manager;
    private UsbSerialPort port;

    public Repository(Context mContext) {
        this.mContext = mContext;
    }

    public List<UsbSerialDriver> getAvailableDrivers() {
        manager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            return null;
        } else {
            return availableDrivers;
        }
    }

    public boolean initializeDrivers() {
        List<UsbSerialDriver> driversList = getAvailableDrivers();

        if (driversList != null) {
            UsbSerialDriver driver = driversList.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            UsbSerialPort serialPort = driver.getPorts().get(0);
            if (serialPort != null) {
                port = serialPort;
                openConnection(connection, port);
                return true;

            }
        }
        return false;
    }

    private void openConnection(UsbDeviceConnection connection, UsbSerialPort port) {
        try {
            port.open(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            port.setParameters(19200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeIntoPort(Button btn) {
        this.btn = btn;

        try {
            port.write(REQUEST_1, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }

        content(port);

    }

    public void content(UsbSerialPort port) {
        counter++;
        byte[] request = new byte[]{(byte) 0x02, (byte) 0x00, (byte) 0x30, (byte) 0x03, (byte) 0x35};   //  get status

        try {
            port.write(request, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            int read = port.read(response, 500);
        } catch (IOException e) {
            e.printStackTrace();

        }

        btnResponse(response);

        refresh(port);
    }

    private void refresh(UsbSerialPort port) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content(port);
            }


        };
        handler.postDelayed(runnable, 1000);

    }

    private void btnResponse(byte[] val) {
        String response = print(val);

        //String  response = val;
        String[] responseArray = response.split(" ");
        String targetResp = responseArray[4].substring(2); //+ responseArray[5].substring(2);
        Log.i("MM", targetResp);
        String respBinary = hexToBin(targetResp);

        Log.i("MM", respBinary);

        Log.v("COUNTER", Character.toString(respBinary.charAt(3)));
        Log.v("COUNTER", Integer.toString(counter));

        if (respBinary.charAt(3) == '1') {
            this.btn.setText("N1  Closed");
            this.btn.setBackgroundColor(0xFFD600);


        } else if (respBinary.charAt(3) == '0') {
            this.btn.setText("N1   Open");
            this.btn.setBackgroundColor(Color.GREEN);
        }
//        if (respBinary.charAt(2) == '1') {
//            btn2.setText("N2  Closed");
//            btn2.setBackgroundColor(0xFFD600);
//        } else if (respBinary.charAt(2) == '0') {
//            btn2.setText("N2   Open");
//            btn2.setBackgroundColor(Color.GREEN);
//        }
//
//        if (respBinary.charAt(1) == '1') {
//            btn3.setText("N3  Closed");
//            btn3.setBackgroundColor(0xFFD600);
//        } else if (respBinary.charAt(1) == '0') {
//            btn3.setText("N3   Open");
//            btn3.setBackgroundColor(Color.GREEN);
//        }
//
//        if (respBinary.charAt(0) == '1') {
//            btn4.setText("N4  Closed");
//            btn4.setBackgroundColor(0xFFD600);
//        } else if (respBinary.charAt(0) == '0') {
//            btn4.setText("N4   Open");
//            btn4.setBackgroundColor(Color.GREEN);
//        }
    }

    private static String hexToBin(String s) {
        String preBin = new BigInteger(s, 16).toString(2);
        Integer length = preBin.length();
        if (length < 4) {
            for (int i = 0; i < 4 - length; i++) {
                preBin = "0" + preBin;
            }
        }
        Log.i("M2", preBin);
        return preBin;
    }

    private static String print(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (byte b : bytes) {
            sb.append(String.format("0x%02X ", b));
        }
        sb.append("]");
        return sb.toString();
    }
}

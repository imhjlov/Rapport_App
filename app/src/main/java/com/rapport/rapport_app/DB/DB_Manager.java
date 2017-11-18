package com.rapport.rapport_app.DB;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Hyunjung on 2017-10-17.
 */

public class DB_Manager {

    private String orderPath;
    //순서 설정을 mysql에 저장할 php를 포함한 도메인 주소를 입력한다.

    private final String order_set_info_orderPath=
            "http://wprkftngh.cafe24.com/order_set.php";

    /*--순서에 저장할 데이터--*/
    private int order_bath;
    private int order_massage;
    private int order_book;
    private int order_lullaby;

    /*--순서저장부분--*/
    public ArrayList<Integer> order_set_info(int order_bath,
                                             int order_massage, int order_book, int order_lullaby){

        orderPath=order_set_info_orderPath;
        this.order_bath=order_bath;
        this.order_massage=order_massage;
        this.order_book=order_book;
        this.order_lullaby=order_lullaby;
//        try{
//            results=new orderSetInfo().excute().get();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }catch (ExecutionException e){
//            e.printStackTrace();
//        }

        //
        return null;

    }

    /*--순서저장부분 데이터 서버 전송--*/
    class orderSetInfo extends AsyncTask<Void, Void, ArrayList<Integer>>{

        @Override
        protected ArrayList<Integer> doInBackground(Void... voids) {

            try{
                URL url = new URL(orderPath);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestMethod("POST");

                String param=
                        "order_bath="+order_bath+"&order_massage="+order_massage+
                                "&order_book="+order_book+"&order_lullaby="+order_lullaby;

                OutputStream outputStream = con.getOutputStream();
                outputStream.write(param.getBytes());
                outputStream.flush();
                outputStream.close();

                BufferedReader rd = null;
                rd=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
                String line=null;
                        while((line=rd.readLine())!=null){
                            Log.d("BufferedReader.",line);
                        }

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(ArrayList<Integer> qResults){
            super.onPostExecute(qResults);
        }
    }


}

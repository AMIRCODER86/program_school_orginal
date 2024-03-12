package org.example;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class SchedulingProblem {
    public  String teacher_avaialability[][][] = new String[12][5][3];
    public  JSONObject json_niaz;
    public void readJsonTeachers(String address_file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(address_file));
        String readjson;
        String json_teacher_read = "";
        while ((readjson = input.readLine()) != null) {
            json_teacher_read = json_teacher_read + readjson;
        }
        JSONObject json_teacher = new JSONObject(json_teacher_read);
        for (int number_teacher = 0 ; number_teacher <12 ; number_teacher++) {
            for (int teacher_day_number = 0 ; teacher_day_number <5 ; teacher_day_number++) {
                for (int bell_number_teachers  = 0 ; bell_number_teachers  <3 ; bell_number_teachers ++) {
                    this.teacher_avaialability[number_teacher][teacher_day_number][bell_number_teachers ] = json_teacher.getJSONObject(String.valueOf(number_teacher)).getJSONObject(String.valueOf(teacher_day_number)).getString(String.valueOf(bell_number_teachers ));
                }
            }
        }
    }
    public  void readJsonNiaz(String address_file) throws IOException {
        String readjson;
        String json_niaz_read = "";
        BufferedReader input = new BufferedReader(new FileReader(address_file));
        while((readjson = input.readLine()) != null){
            json_niaz_read=json_niaz_read+readjson;
        }
        this.json_niaz =new JSONObject(json_niaz_read);
    }
}
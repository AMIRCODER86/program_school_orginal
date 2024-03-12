package org.example;

import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SchedulingSolution {
    boolean compeleted = false;
    public JSONObject json_class1;
    public  String class1[][] = new String[5][3];
    private  HashMap<String, Integer> assigned_hours = new HashMap<String, Integer>();
    private  SchedulingProblem problem = new SchedulingProblem();
    public void readJsonclass1(String address_file) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(address_file));
        String json_class1_read = "";
        String readjson;
        while ((readjson = input.readLine()) != null) {
            json_class1_read = json_class1_read + readjson;
        }
        this.json_class1 = new JSONObject(json_class1_read);
    }
    public void getArrayClass(){
        for (int class_day_number = 0 ; class_day_number < 5 ; class_day_number++) {
            for (int bell_number_class = 0 ; bell_number_class < 3 ; bell_number_class++) {
                class1[class_day_number][bell_number_class] = json_class1.getJSONObject(String.valueOf(class_day_number)).getString(String.valueOf(bell_number_class));
            }
        }
    }
    public void print() throws FileNotFoundException {
        //PrintWriter print=new PrintWriter(address_file);
        for (int class_day_number = 0 ; class_day_number < 5 ; class_day_number++ ){
            for (int bell_number_class = 0 ; bell_number_class < 3 ; bell_number_class++){
                System.out.println(class_day_number+"_"+bell_number_class+class1[class_day_number][bell_number_class]);
            }
        }
    }
    public int[] getNextHour(){
        int next_hour[] = {-1, -1} ;
        for(int class_day_number = 0; class_day_number < 5; class_day_number++){
            for (int bell_number_class = 0; bell_number_class < 3; bell_number_class++){
                if (class1[class_day_number][bell_number_class].equals("null")) {
                    next_hour[0] = class_day_number;
                    next_hour[1] = bell_number_class;
                    return next_hour;
                }
            }
        }
        return next_hour;
    }
    public  void assignHour(int day, int hour, String lesson){
        assigned_hours.replace(lesson, assigned_hours.get(lesson)+1);
        class1[day][hour] = lesson;
    }
    public  ArrayList<String> getListLeson(int day, int hour) throws IOException {
        problem.readJsonNiaz("D:\\niaz.txt");
        problem.readJsonTeachers("D:\\json_teacher.txt");
        ArrayList <String> list_lesson = new ArrayList<String>();
        for (int counter_teacher = 0; counter_teacher < 12; counter_teacher++){
            for (int counter_lesson = 0; counter_lesson < 12; counter_lesson++) {
                String name_lesson = problem.json_niaz.getJSONObject(String.valueOf(counter_lesson)).getString("0");
                int number_niaz = Integer.valueOf(problem.json_niaz.getJSONObject(String.valueOf(counter_lesson)).getString("niaz"));
                String name_teacher = problem.teacher_avaialability[counter_teacher][day][hour];
                if (name_teacher.equals(name_lesson)){
                    if (assigned_hours.containsKey(name_lesson)){
                        if ((assigned_hours.get(name_lesson)) < (Integer.valueOf(number_niaz))) {
                            list_lesson.add(name_lesson);
                        }
                    }
                    else {
                        assigned_hours.put(name_lesson, 0);
                        list_lesson.add(name_lesson);
                    }
                }
            }
        }
        return list_lesson;
    }
}
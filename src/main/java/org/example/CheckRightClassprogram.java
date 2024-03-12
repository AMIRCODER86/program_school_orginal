package org.example;

import java.io.IOException;
public class CheckRightClassprogram {
    public static boolean checker(SchedulingProblem problem, SchedulingSolution solution) throws IOException {
        String class1[][] = new String[5][3];
        class1 = solution.class1;
        String teachers_array[][][] = new String[12][5][3];
        teachers_array = problem.teacher_avaialability;
        int class_day_number = 0;
        int bell_number_class = 0;
        boolean finish_fors = false;
        boolean check_true_or_false = true;
        for (int number_teacher = 0; number_teacher < 12; number_teacher++) {
            if (finish_fors == true) {
                break;
            }
            for (int teacher_day_number = 0; teacher_day_number < 5; teacher_day_number++) {
                if (finish_fors == true) {
                    break;
                }
                for (int bell_number_teachers = 0; bell_number_teachers < 3; bell_number_teachers++) {
                    if (teachers_array[number_teacher][teacher_day_number][bell_number_teachers].equals(class1[class_day_number][bell_number_class])) {
                        if (teacher_day_number == class_day_number && bell_number_teachers == bell_number_class) {
                            number_teacher = 0;
                            teacher_day_number = 0;
                            bell_number_teachers = 0;
                            bell_number_class++;
                            if (bell_number_class == 3) {
                                class_day_number++;
                                bell_number_class = 0;
                                if (class_day_number == 5) {
                                    finish_fors = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (number_teacher == 11 && teacher_day_number == 4 && bell_number_teachers == 2) {
                        finish_fors = true;
                        check_true_or_false = false;
                        break;
                    }


                }
            }
        }

        if (check_true_or_false == true) {
            for (int classDayNumber2 = 0; classDayNumber2 < 5; classDayNumber2++) {
                if (class1[classDayNumber2][0].equals(class1[classDayNumber2][1]) && class1[classDayNumber2][0].equals(class1[classDayNumber2][2])) {
                    check_true_or_false = false;
                    break;
                }
            }
        }

        if (check_true_or_false == true) {
            String name_niaz;
            int number_niaz;
            for (int counter = 0; counter < 12; counter++) {
                int counter_niaz = 0;
                finish_fors = false;
                name_niaz = problem.json_niaz.getJSONObject(String.valueOf(counter)).getString("0");
                number_niaz = problem.json_niaz.getJSONObject(String.valueOf(counter)).getInt("niaz");
                for (class_day_number = 0; class_day_number < 5; class_day_number++) {
                    if (finish_fors == true) {
                        break;
                    }
                    for (bell_number_class = 0; bell_number_class < 3; bell_number_class++) {
                        if (class1[class_day_number][bell_number_class].equals(name_niaz)) {
                            counter_niaz++;
                        }
                        if (counter_niaz >= number_niaz) {
                            finish_fors = true;
                            break;
                        }
                    }
                }
                if (finish_fors == false) {
                    check_true_or_false = false;
                    break;
                }
            }
        }
        return check_true_or_false;
    }
}
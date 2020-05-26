package com.course.integration.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public final class SerializableTeacherSubjectConnection implements Serializable {

    private final String courseName;
    private final String teacherId;
    private final ArrayList<String> studentsId;

    public SerializableTeacherSubjectConnection(@JsonProperty("courseName") String courseName,
                                                @JsonProperty("teacherId")String teacherId,
                                                @JsonProperty("studentId") ArrayList<String> studentsId) {
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.studentsId = studentsId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public ArrayList<String> getStudentsId() {
        return studentsId;
    }

}

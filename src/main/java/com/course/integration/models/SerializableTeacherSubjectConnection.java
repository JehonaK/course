package com.course.integration.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public final class SerializableTeacherSubjectConnection implements Serializable {

    private final String courseName;
    private final String teacherId;
    private final String subjectId;
    private final ArrayList<String> studentsId;

    public SerializableTeacherSubjectConnection(@JsonProperty("courseName") String courseName,
                                                @JsonProperty("teacherId")String teacherId,
                                                @JsonProperty("subjectId") String subjectId,
                                                @JsonProperty("studentId") ArrayList<String> studentsId) {
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.studentsId = studentsId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public ArrayList<String> getStudentsId() {
        return studentsId;
    }

    @Override
    public String toString() {
        return "SerializableTeacherSubjectConnection{" +
                "courseName='" + courseName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", studentsId=" + studentsId +
                '}';
    }

}

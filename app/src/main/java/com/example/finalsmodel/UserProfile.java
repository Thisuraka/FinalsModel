package com.example.finalsmodel;

import android.provider.BaseColumns;

public class UserProfile {

    private UserProfile() {
    }

    public static class Users implements BaseColumns {
        private String username;
        private String password;
        private String dob;
        private String Gender;

        public Users() {
        }

        public Users(String username, String password, String dob, String gender) {
            this.username = username;
            this.password = password;
            this.dob = dob;
            this.Gender = gender;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }
    }
}

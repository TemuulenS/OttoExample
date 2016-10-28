package com.example.admin.ottoexample.events;

import com.example.admin.ottoexample.model.TestData;

/**
 * Created by admin on 10/27/2016.
 */

public class Events {

    public static class FragmentActivityMessage {

        private String message;

        public FragmentActivityMessage(TestData testData) {
            this.message = testData.message;
        }
        public String getMessage() {
            return message;
        }
    }
    public static class ActivityFragmentMessage {

        private String message;

        public ActivityFragmentMessage(TestData testData) {
            this.message = testData.message;
        }

        public String getMessage() {
            return message;
        }
    }
}
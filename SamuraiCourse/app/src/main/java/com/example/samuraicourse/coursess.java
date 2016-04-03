package com.example.samuraicourse;

/**
 * Created by dgist on 4/3/2016.
 */
public class coursess {
        /**
         *
         * Item crn
         * Item number
         * Item title
         * Item units
         * Item activity
         * Item days
         * Item time
         * Item room
         * Item length
         * Item instruction
         * Item maxEnrl
         * Item seatsAvailable
         * Item activeEnrl
         * Item sem_id
         *
         */
        @com.google.gson.annotations.SerializedName("id")
        private String mId;
        @com.google.gson.annotations.SerializedName("crn")
        private Integer crn;
        @com.google.gson.annotations.SerializedName("number")
        private String number;
        @com.google.gson.annotations.SerializedName("title")
        private String title;
        @com.google.gson.annotations.SerializedName("units")
        private int units;
        @com.google.gson.annotations.SerializedName("activity")
        private String activity;
        @com.google.gson.annotations.SerializedName("days")
        private String days;
        @com.google.gson.annotations.SerializedName("time")
        private String time;
        @com.google.gson.annotations.SerializedName("room")
        private String room;
        @com.google.gson.annotations.SerializedName("length")
        private String length;
        @com.google.gson.annotations.SerializedName("instruction")
        private String instruction;
        @com.google.gson.annotations.SerializedName("maxEnrl")
        private int maxEnrl;
        @com.google.gson.annotations.SerializedName("seatsAvailable")
        private int seatsAvailable;
        @com.google.gson.annotations.SerializedName("activeEnrl")
        private int activeEnrl;
        @com.google.gson.annotations.SerializedName("sem_id")
        private int sem_id;

        public coursess(){

        }

        public coursess(String id,int crn){
            this.setId(id);
            this.setCrn(crn);
            this.setNumber(number);
            this.setTitle(title);
            this.setUnits(units);
            this.setActivity(activity);
            this.setDays(days);
            this.setTime(time);
            this.setRoom(room);
            this.setLength(length);
            this.setInstruction(instruction);
            this.setMaxEnrl(maxEnrl);
            this.setSeatsAvailable(seatsAvailable);
            this.setActiveEnrl(activeEnrl);
            this.setSem_id(1);
        }

        public Integer getCrn() {
            return crn;
        }

        public String getNumber() {
            return number;
        }

        public String getTitle() {
            return title;
        }

        public int getUnits() {
            return units;
        }

        public String getActivity() {
            return activity;
        }

        public String getDays() {
            return days;
        }

        public String getTime() {
            return time;
        }

        public String getRoom() {
            return room;
        }

        public String getLength() {
            return length;
        }

        public String getInstruction() {
            return instruction;
        }

        public int getMaxEnrl() {
            return maxEnrl;
        }

        public int getSeatsAvailable() {
            return seatsAvailable;
        }

        public int getActiveEnrl() {
            return activeEnrl;
        }

        public int getSem_id() {
            return sem_id;
        }

        public String getId() {
            return mId;
        }

        public void setId(String id){this.mId = id;}

        public void setCrn(Integer crn) {
            this.crn = crn;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUnits(int units) {
            this.units = units;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setRoom(String room) {
            this.room = room;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public void setInstruction(String instruction){
            this.instruction = instruction;
        }

        public void setMaxEnrl(int maxEnrl) {
            this.maxEnrl = maxEnrl;
        }

        public void setSeatsAvailable(int seatsAvailable) {
            this.seatsAvailable = seatsAvailable;
        }

        public void setActiveEnrl(int activeEnrl) {
            this.activeEnrl = activeEnrl;
        }

        public void setSem_id(int sem_id) {
            this.sem_id = sem_id;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof coursess && ((coursess) o).mId == mId;
        }
}

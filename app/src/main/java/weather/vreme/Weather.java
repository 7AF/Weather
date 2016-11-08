package weather.vreme;

public class Weather {

    public static Location location;
    public static Condition currentCondition = new Condition();
    public static Temperature temperature = new Temperature();
    public static Wind wind = new Wind();
    public Condition1 currentCondition1 = new Condition1();
    public Rain rain = new Rain();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

    public static byte[] iconData;

    public class Condition1 {
        private String descr1;
        private String icon1;

        private float pressure1;
        private float humidity1;

        public String getDescr1() {
            return descr1;
        }

        public void setIcon1(String icon1) {
            this.icon1 = icon1;
        }

        public void setDescr1(String descr1) {
            this.descr1 = descr1;
        }

        public String getIcon1() {
            return icon1;
        }

        public float getPressure1() {
            return pressure1;
        }

        public void setPressure1(float pressure1) {
            this.pressure1 = pressure1;
        }

        public float getHumidity1() {
            return humidity1;
        }

        public void setHumidity1(float humidity1) {
            this.humidity1 = humidity1;
        }

    }

    public static class Condition {
        private int weatherId;
        private String condition;
        private String descr;
        private String icon;

        private float pressure;
        private float humidity;

        public int getWeatherId() {
            return weatherId;
        }

        public void setWeatherId(int weatherId) {
            this.weatherId = weatherId;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getDescr() {
            return descr;
        }

        public void setDescr(String descr) {
            this.descr = descr;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }


    }

    public static class Temperature {
        private float temp;
        private float minTemp;
        private float maxTemp;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }

    }

    public static class Wind {
        private float speed;
        private float deg;

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getDeg() {
            return deg;
        }

        public void setDeg(float deg) {
            this.deg = deg;
        }


    }

    public class Rain {
        private String time;
        private float ammount;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getAmmount() {
            return ammount;
        }

        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }


    }

    public class Snow {
        private String time;
        private float ammount;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public float getAmmount() {
            return ammount;
        }

        public void setAmmount(float ammount) {
            this.ammount = ammount;
        }


    }

    public class Clouds {
        private int perc;

        public int getPerc() {
            return perc;
        }

        public void setPerc(int perc) {
            this.perc = perc;
        }


    }

}
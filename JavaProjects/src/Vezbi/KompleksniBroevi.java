package Vezbi;

public class KompleksniBroevi {
    class ComplexNumber<T extends Number,U extends Number> implements Comparable<ComplexNumber<?,?>>{
        private T real;
        private U imaginary;

        public ComplexNumber(T real, U imaginary){
            this.real=real;
            this.imaginary=imaginary;
        }
        public T getReal(){
            return real;
        }
        public U getImaginary(){
            return imaginary;
        }
        public double modul(){
            return Math.sqrt(real.doubleValue()*real.doubleValue()+imaginary.doubleValue()*imaginary.doubleValue());
        }
        public int compareTo(ComplexNumber<?, ?> o){
            if(this.modul()>o.modul()){
                return 1;
            }
            else if(this.modul()<o.modul()){
                return -1;
            }
            return 0;
        }
        public String toString(){
            StringBuilder sb = new StringBuilder();
            if(imaginary.doubleValue()>0){
                sb.append(String.format("%.2f+%.2fi",real.doubleValue(),imaginary.doubleValue()));
            }
            else{
                sb.append(String.format("%.2f%.2fi",real.doubleValue(),imaginary.doubleValue()));
            }
            return sb.toString();
        }
    }
}

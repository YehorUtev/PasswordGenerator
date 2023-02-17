package main;

import java.io.*;

public class Code {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.println("Menu: ");
            System.out.println("0.Exit");
            System.out.println("1.Generate password and choose folder");
            int action = Integer.parseInt(reader.readLine());
            if(action == 1){
                System.out.println("Please input name of website to generate password: ");
                String website = reader.readLine();
                System.out.println("Please input length of password(minimal length 8): ");
                try{
                    int length = Integer.parseInt(reader.readLine());
                    if(length < 8){
                        System.out.println("Wrong password length, enter 8 or more");
                    }else {
                        System.out.println("Please input file name: ");
                        String folder = reader.readLine();
                        FileOutputStream outputStream = new FileOutputStream(folder);
                        ByteArrayOutputStream password = getPassword(length);
                        String line = password.toString();
                        StringBuilder builder = new StringBuilder();
                        builder.append(website + ": " + line);
                        outputStream.write(builder.toString().getBytes());
                        System.out.println("Password successfully generated");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Wrong data type, enter integer");
                }

            }if(action == 0){
                break;
            }
        }
    }
    public static ByteArrayOutputStream getPassword(int length){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Basket[] baskets = new Basket[3];
        baskets[0] = new Basket('0','9');
        baskets[1] = new Basket('a','z');
        baskets[2] = new Basket('A','Z');
        //baskets[0] = new Basket((char)33, (char)64);
        int i = 0;
        for(;i < 5; i++){
            int index = (int)(Math.random()*3);
            generateChar(outputStream,baskets,index);
        }
        for(int j = 0; j < baskets.length; j++){
            generateChar(outputStream,baskets,j);
            i++;
        }
        while(i < length){
            int index = (int)(Math.random()*3);
            generateChar(outputStream,baskets,index);
            i++;
        }
        return outputStream;
    }
    public static void generateChar(ByteArrayOutputStream is, Basket[] baskets, int index){
        Basket basket = baskets[index];
        is.write((char)basket.getRandom());
    }
    public static class Basket{
        int begin;
        int quantity;

        private Basket(char begin, char end){
            this.begin = begin;
            this.quantity = end - begin + 1;
        }
        public int getRandom(){
            return (int)(Math.random()*quantity) + begin;
        }
    }
}

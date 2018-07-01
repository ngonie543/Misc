class IntToWords{
    static String [] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static String [] tens = {"zero", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static String [] teens = {"zero", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    static String [] subgroups = {"billion", "million", "thousand", ""};
    static boolean teenPresent = false;

    public static void main(String [] args){
        int n =30037307;     
        String result = stringIntToWords(n);
        System.out.println(result);
    }

    public static String stringIntToWords(int n){
        String normalizedInput = normalizeString(n);
        String word = stringIntToWords(normalizedInput);
        return word;
    }



    public static String normalizeString(int n){
        String input = Integer.toString(n);
        int diff = 12 - input.length();
        String extension ="";
        for(int i=0; i<diff; i++){
            extension= extension + "0";
        }
        return extension+input;
    }



    public static String stringIntToWords(String str){  //str has 12 places
        String word = "";
        for(int i=0; i<12; i=i+3){
            String subgroup = str.substring(i,i+3);
            //System.out.println(subgroup);
            if(read3(subgroup).equals("zero")){ // to avoid zero billion, zero million .........
                word = word +"";
            }else{
            String subword = read3(subgroup) ;
            
            if(!subword.equals("") && i!=9){
            subword=subword+" " + subgroups[i/3]+", ";
            }
            //System.out.println(subword);
            word = word + subword;
            }
        }
        return word.substring(0, word.length()); //return and remove comma at the end
    }




    public static String read3(String str){

        String word = "";
        //Hundreds
        String hundreds =getHundreds(str);
        word= word+hundreds;
        //Tens
        String tens ="";
        if(!str.substring(0,2).equals("00")){
            tens =getTens(str);
        }
        word= word+tens;
       //Digits for ten!=zero and ten!=ten

        String digit = "";
        if(!teenPresent){
            digit =getDigits(str);
        }
        word= word+digit;
        teenPresent = false;
        return word;
    }


    public static String getHundreds(String str){
        String word = "";
        int hundredIndex = Integer.parseInt(str.substring(0,1));
        String hundred = digits[hundredIndex];

        if(!hundred.equals("zero")){
            word = word + hundred + " hundred" + " and ";
        }
        return word;
    }

    public static String getTens(String str){
        String word = "";
        int tensIndex = Integer.parseInt(str.substring(1,2));
        String ten = tens[tensIndex];


        if(ten=="ten"){
            teenPresent = true;
            int teensIndex = Integer.parseInt(str.substring(2,3));
            String teen = teens[teensIndex];
            if(teen!= "zero"){
                word = word + "" + teen;
            }


            return word;
        }      
        if(ten!= "zero" || !ten.equals("ten")){
            word = word + "" + ten;
        }
        return word;
    }


    public static String getDigits(String str){
        String word = "";
        int digitsIndex = Integer.parseInt(str.substring(2,3));
        String digit = digits[digitsIndex];
        
        if(digit!="zero"){
            word = word + " " + digit;
        }
        return word;
    }




}

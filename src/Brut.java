public class Brut {



        private Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        private char[] decodedText;
        private String[] plainText;
        private java.util.List<Character> alphabetList;

        public Brut(){
            alphabetList = java.util.Arrays.asList(alphabet);
            plainText = new String[alphabet.length];
        }

        public String[] producePlaintext(String cipherText) {
            //поместите каждую букву зашифрованного текста в массив символов в формате верхнего регистра
            char[] message = cipherText.toUpperCase().toCharArray();
            //перебирайте все возможные ключи
            for (int key = 0; key < alphabet.length; key++) {
                //установите значение расшифрованного массива символов таким же, как длина зашифрованного текста
                decodedText = new char[message.length];
                //перебирайте символы зашифрованного текста
                for (int i = 0; i < message.length; i++) {

                    //если символ не является пробелом
                    if (message[i] != ' ') {
                        //сдвиньте буквы
                        decodedText[i] = alphabet[(alphabetList.indexOf(message[i])+key) % alphabet.length];
                    }else{
                        decodedText[i] = ' ';
                    }
                }
                plainText[key] = String.valueOf(decodedText);
            }
            return plainText;
        }

        public static void main(String[] args) {
            Brut t = new Brut();
            for(String pt : t.producePlaintext("Olssv Hspzh")) {
                System.out.println(pt);
            }
        }

    }


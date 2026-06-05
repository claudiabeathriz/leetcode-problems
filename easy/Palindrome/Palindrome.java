class Palindrome {
    public boolean isPalindrome(int x) {

        String numero = String.valueOf(x);
        int size = numero.length();

        for (int i = 0; i < (size / 2); i++){
            if(numero.charAt(i) != numero.charAt(size-1-i)){
                return false;
            }
        }

        return true;
    }
}
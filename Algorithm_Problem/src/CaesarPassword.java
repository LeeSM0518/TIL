public class CaesarPassword {
    class Solution {
        public String solution(String s, int n) {
            String answer = "";
            char[] password;
            password = s.toCharArray();
            int sum;

            for(int i=0; i<s.length(); i++) {
                if(Character.isUpperCase(password[i])) {
                    sum = (int)password[i] - 64 + n;
                } else {
                    sum = (int)password[i] - 96 + n;
                }


                if(password[i] == ' ') {
                }
                else if(sum > 26) {
                    sum %= 26;
                    if (password[i] < 97) password[i] = (char) ('A' + sum-1);
                    else password[i] = (char) ('a' + sum -1);
                }
                else password[i] += n;
            }

            answer = String.valueOf(password);

            return answer;
        }
    }
}

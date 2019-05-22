package system_class.exit_method;

public class ExitExample {
    public static void main(String[] args) {
        // 보안 관리자 설정
        System.setSecurityManager(new SecurityManager(){
            // 종료 상태 값이 5가 아닐땐 예외 발생
            @Override
            public void checkExit(int status) {
                if(status != 5) {
                    throw new SecurityException();
                }
            }
        });

        for(int i=0; i<10; i++) {
            // i 값 출력
            System.out.println(i);

            try {
                // exit 메소드 실행
                System.exit(i);
            } catch(SecurityException e){ } // 예외 처리
        }
    }
}

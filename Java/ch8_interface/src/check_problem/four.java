package check_problem;

interface DataAccessObject {
    void select();
    void insert();
    void update();
    void delete();
}

class OracleDao implements DataAccessObject {
    public void select() {
        System.out.println("Oracle DB에서 검색");
    }
    public void insert() {
        System.out.println("Oracle DB에 삽입");
    }
    public void update() {
        System.out.println("Oracle DB를 수정");
    }
    public void delete() {
        System.out.println("Oracle DB에서 삭제");
    }
}

class MySqlDao implements DataAccessObject {
    public void select() {
        System.out.println("MySql DB에서 검색");
    }
    public void insert() {
        System.out.println("MySql DB에 삽입");
    }
    public void update() {
        System.out.println("MySql DB를 수정");
    }
    public void delete() {
        System.out.println("MySql DB에서 삭제");
    }
}

class DaoExample {
    public static void dbWork(DataAccessObject dao) {
        dao.select();
        dao.insert();
        dao.update();
        dao.delete();
    }

    public static void main(String[] args) {
        dbWork(new OracleDao());
        dbWork(new MySqlDao());
    }
}
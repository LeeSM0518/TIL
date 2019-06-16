#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define PRINT_STUDENTS 1
#define SEARCH_STUDENT_ID 2
#define EXIT 3

// 학생 수 카운터
int studentCount = 0;

// 학생 정보 구조체
typedef struct Student {
    char studentId[20];         // 학번
    char name[20];              // 이름
    int attendance[15];         // 출석
    int attendanceScore;        // 출석 점수
    int midtermExamScore;       // 중간 고사 점수
    int finalExamScore;         // 기말 고사 점수
    int rank;                   // 등수
    char grade;                 // 학점
    double totalScore;          // 전체 점수
} Student;

Student* loadStudentList();     // 파일 입출력을 통해 학생정보.txt 를 불러온다
void createStudent(Student* student, char* string);     // 불러온 파일로 부터 학생 정보를 생성한다
void printStudentList(Student* studentList);            // 학생 정보들을 출력시킨다
int returnAttendanceScore(int attendance[]);            // 출석 배열로 부터 출석 점수를 구한 뒤, 반환
void printMenu(Student* studentList);                   // 메뉴 화면을 출력시킨다
double returnTotalScore(int attendance, int midterm, int final);    // 출석 점수, 중간, 기말 점수로 부터 전체 점수를 구한 뒤, 반환한다
void rankStudent(Student* studentList);                 // 학생들의 등수를 매긴다
int gradeCalculation(int percent);                      // 학점 퍼센트를 계산후 학점당 명수 반환
void searchStudent(Student* studentList);               // 학생 검색 함수

int main(void) {
    Student *studentList;               // 학생 구조체 포인터
    studentList = loadStudentList();    // 파일 입출력으로 학생들 정보 불러오는 함수 호출
    rankStudent(studentList);           // 학생들 등수를 매기는 함수 호출

    printMenu(studentList);             // 메뉴 출력
}

Student* loadStudentList() {
    Student* studentList;           // 학생 구조체 포인터

    char string[100][100];          // 파일로 부터 입력받을 문자 이차원 배열
    int i;

    FILE *fp = fopen("학생정보.txt", "r");    // 학생정보.txt 파일을 읽기 모드로 열기.

    while (1) {
        fgets(string[studentCount], 100, fp);   // char형 배열에 파일의 내용을 한줄씩 입력시킨다.

        // 만약 파일의 끝에 도달하면 파일 읽기 종료
        if (feof(fp)) break;

        // 학생 수 카운트
        studentCount++;
    }

    fclose(fp);    // 파일 포인터 닫기

    // 학생 수 만큼 동적 배열 할당
    studentList = (Student*)malloc(sizeof(studentList) * studentCount);

    // 읽어 온 파일 내용을 토대로 학생 정보 생성
    for (i = 0; i < studentCount; i++) {
        createStudent(&studentList[i], string[i]);
    }

    return studentList;
}

void createStudent(Student* student, char* string) {
    char tokens[100][100];
    int i = 0;
    int j = 0;

    char *ptr = strtok(string, " ");      // " " 공백 문자를 기준으로 문자열을 자름, 포인터 반환
    strcpy(tokens[i++], ptr);             // 첫 번째 잘린 문자열 배열에 저장

    while (ptr != NULL)               // 자른 문자열이 나오지 않을 때까지 반복
    {
        ptr = strtok(NULL, " ");      // 다음 문자열을 잘라서 포인터를 반환
        if (ptr != NULL) {
            strcpy(tokens[i++], ptr);   // 잘린 문자열을 배열에 저장
        }
    }

    strcpy(student->studentId, tokens[0]);  // 학번 저장
    strcpy(student->name, tokens[1]);       // 이름 저장

    for (i = 2; i < 17; i++) {
        student->attendance[j++] = atoi(tokens[i]);     // 춠석 정보 저장
    }

    student->attendanceScore = returnAttendanceScore(student->attendance);  // 출석 배열로 출석 점수 생성

    student->midtermExamScore = atoi(tokens[17]);   // 중간 고사 점수
    student->finalExamScore = atoi(tokens[18]);     // 기말 고사 점수

    // 출석 점수, 중간, 기말 점수를 퍼센트로 나눠서 더한 총 점수 계산
    student->totalScore = returnTotalScore(
            student->attendanceScore,
            student->midtermExamScore,
            student->finalExamScore
            );

    // 총 점수나 출석 점수가 어느정도에 못 미치는 경우 후처리
    if (student->totalScore < 60 || student->attendanceScore < 85) {
        student->rank = -1;
        student->grade = 'F';
    }

}

// 학생 정보 출력 함수
void printStudentList(Student* studentList) {
    int i = 0;

    printf("학번\t\t이름\t점수\t학점\t등수\n");       // 출력 시킬 내용들

    for (i = 0; i < studentCount; i++) {
        // \t 는 Tab
        // %.2 는 소수점 둘째짜리 까지만 출력
        printf("%s", studentList[i].studentId);
        printf("\t%s", studentList[i].name);
        printf("\t%.2lf", studentList[i].totalScore);
        printf("\t%c", studentList[i].grade);
        printf("\t%d", studentList[i].rank);
        printf("\n");
    }
}

// 출석 점수 계산
int returnAttendanceScore(int attendance[]) {
    int countAbsent = 0, i, score;

    // 결석 수를 계산한다
    for (i = 0; i < studentCount; i++) {
        if (attendance[i] == 1) countAbsent++;
    }

    // 결석 수에 대한 후처리
    if (countAbsent > 3) score = 0;
    else {
        score = 100 - 5 * countAbsent;
    }

    return score;
}

// 메뉴 출력
void printMenu(Student* studentList) {
    int menu = 0;

    while (menu != 3) {
        printf("====================[성적 계산 프로그램]===================\n\n");
        printf("\t1. 전체 학생 성적 보기\n");
        printf("\t2. 학번 검색\n");
        printf("\t3. 종료\n\n");
        printf("\t> ");
        scanf("%d", &menu);
        printf("\n");

        if (menu == 1) {
            // 학생 정보 출력
            printStudentList(studentList);
        } else if (menu == 2) {
            // 학생 정보 검색
            searchStudent(studentList);
        }
    }

}

// 총 점수 계산 함수
double returnTotalScore(int attendance, int midterm, int final) {
    // 출석 점수 % 20
    // 중간 점수 % 40
    // 기말 점수 % 40
    return (attendance * 0.2) + (midterm * 0.4) + (final * 0.4);
}

// 학생들의 등 수를 매기는 함수
void rankStudent(Student* studentList) {
    int i, j;
    int exceptStudentCount = 0;         // F를 맞은 학생들
    int gradeA, gradeB;                 // A 학점, B 학점 커트라인 명 수

    gradeA = gradeCalculation(30);      // 학생 수 % 30
    gradeB = gradeCalculation(70);      // 학생 수 % 70

    // F 맞은 학생들 구함
    for (i = 0; i < studentCount; i++)
        if (studentList[i].rank == -1) exceptStudentCount++;

    // 학생들 등수를 매긴다
    for (i = 0; i < studentCount; i++) {
        // 만약 F를 맞은 학생이면 다음 학생으로 이동
        if (studentList[i].rank == -1) continue;

        for (j = 0; j < studentCount; j++) {
            // i와 j가 같으면 자기 자신이므로 자기 자신의 등수를 1 올리고 다음으로
            if (i == j) {
                studentList[i].rank++;
                continue;
            }
            // 만약 비교할 학생이 F이면 다음 학생으로
            else if (studentList[j].rank == -1) continue;
            // 만약 총 점수가 더 낮은 학생이 있다면 그 학생의 등수를 1 올린다
            else if (studentList[j].totalScore < studentList[i].totalScore) studentList[j].rank++;
        }
    }

    for (i = 0; i < studentCount; i++) {
        // 등수가 -1 이면 다음 학생으로 이동
        if (studentList[i].rank == -1) continue;

        // A 학점 학생 커트라인 명수보다 같거나 낮으면 A 학점
        else if (studentList[i].rank <= gradeA) studentList[i].grade = 'A';

        // A 커트라인 명 수 보다 높고 B 커트라인 명 수 보다 같거나 낮으면 B 학점
        else if (studentList[i].rank <= gradeB) studentList[i].grade = 'B';

        // 위의 경우들을 제외한 사람들은 C 학점
        else studentList[i].grade = 'C';
    }
}

// 퍼센트에 대한 학점 커트라인 명수 반환 함수
int gradeCalculation(int percent) {
    double gradeCount;

    gradeCount = studentCount * percent * 0.01;

    return (int) floor(gradeCount);
}

// 학생 검색 함수
void searchStudent(Student* studentList) {
    char searchStudentId[100];      // 검색할 학번
    int i, success = 0;

    // 학번을 입력받는다
    printf("검색할 학번 : ");
    scanf("%s", searchStudentId);
    printf("\n");

    for (i = 0; i < studentCount; i++) {
        // 만약 입력한 학번과 일치할 경우
        if (strcmp(studentList[i].studentId, searchStudentId) == 0) {
            printf("학번\t이름\t점수\t학점\t등수\n");
            printf("%s", studentList[i].studentId);
            printf("\t%s", studentList[i].name);
            printf("\t%.2lf", studentList[i].totalScore);
            printf("\t%c", studentList[i].grade);
            printf("\t%d", studentList[i].rank);
            printf("\n");

            // 검색 성공
            success = 1;
            break;
        }
    }

    // 검색 실패시 출력
    if (success == 0) printf("없는 학번 입니다.\n");
}
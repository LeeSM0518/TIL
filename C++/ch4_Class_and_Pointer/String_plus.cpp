#include <iostream>
#include <string>
using namespace std;

int main() {
	string a = "Java", b = "C++";
	cout << "a = " << a << "\nb = " << b << endl;
	a = b;
	cout << "문자열 치환 a = b" << endl;
	cout << "a = " << a << "\nb = " << b << endl;

	string name = "Kitae";
	string alias = "Kito";

	int res = name.compare(alias);
	cout << "\n문자열 사전적 비교법";
	if (res == 0) cout << "두 문자열이 같다." << endl;
	else if (res < 0) cout << name << " < " << alias << endl;	// name이 앞에 옴
	else cout << alias << " < " << name << endl;	// name이 뒤에 옴

	cout << "\n문자열 비교법" << endl;
	if (name == alias) cout << "두 문자열이 같다." << endl;
	else cout << name << " 과 " << alias << " 는 " << "두 문자열이 다르다.\n" << endl;

	cout << "문자열 연결" << endl;
	string link("I");
	link.append(" love ");	
	cout << link << endl;

	cout << "\n문자열 삭제" << endl;
	string test_1("I love C++");
	test_1.erase(0, 2);		// 처음부터 2개의 문자 삭제
	cout << test_1 << endl;
	test_1.clear();		// a = " "
	cout << test_1 << endl;

	cout << "\n문자열 서브스트링" << endl;
	string test_2("I love C++");
	string test_3 = test_2.substr(2, 4);	// test2 의 인덱스 2에서 4개의 문자 리턴
	string test_4 = test_2.substr(2);		// test2 의 인덱스 2에서 끝까지 리턴
	cout << test_2 << endl;
	cout << test_3 << endl;
	cout << test_4 << endl;

	string e = "I love love C++";
	int index = e.find("love"); // e에서 "love" 검색. 인덱스 2 리턴
	index = e.find("love", index+1); // e의 인덱스 3 부터 "love" 검색, 인덱스 7 리턴
	index = e.find("C#"); // e에서 "C#"을 발견할 수 없음. -1 리턴
	index = e.find('v', 7); // e의 인덱스 7부터 ‘v’ 문자 검색, 인덱스 9 리턴
	string f("I love C++");
	char ch1 = f.at(7);		// 문자열 f의 인덱스 7에 있는 문자 리턴
	char ch2 = f[7];		// f.at(7) 과 동일
	char ch3 = f.at(f.length() - 1);	// 문자열 f의 마지막 문자.
}
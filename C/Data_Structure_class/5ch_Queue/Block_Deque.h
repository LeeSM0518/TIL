#define TRUE 1
#define FALSE 0

typedef struct Block {
	int grade;
	char color;
}Block;		// 요소의 타입

typedef struct DlistNode {	// 노드의 타입
	Block data;
	struct DlistNode *llink;
	struct DlistNode *rlink;
}DlistNode;

typedef struct DequeType {	// 덱의 타입
	DlistNode *head;
	DlistNode *tail;
	int result;
	int broken;
}DequeType;

void error(char* message);
DlistNode *create_node(DlistNode *llink, Block item, DlistNode *rlink);
void add_rear(DequeType *dq, Block item);
void add_front(DequeType *dq, Block item);
Block delete_front(DequeType *dq);
Block delete_rear(DequeType *dq);
void init(DequeType *dq);
int is_empty(DequeType *dq);
void display(DequeType *dq);
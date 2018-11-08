#define TRUE 1
#define FALSE 0

typedef int element;		// 요소의 타입

typedef struct DlistNode {	// 노드의 타입
	element data;
	struct DlistNode *llink;
	struct DlistNode *rlink;
}DlistNode;

typedef struct DequeType{	// 덱의 타입
	DlistNode *head;
	DlistNode *tail;
}DequeType;

void error(char* message);
DlistNode *create_node(DlistNode *llink, element item, DlistNode *rlink);
void add_rear(DequeType *dq, element item);
void add_front(DequeType *dq, element item);
element delete_front(DequeType *dq);
element delete_rear(DequeType *dq);
void init(DequeType *dq);
int is_empty(DequeType *dq);
void display(DequeType *dq);
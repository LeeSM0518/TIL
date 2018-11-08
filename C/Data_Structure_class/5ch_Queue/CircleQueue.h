#define MAX_QUEUE_SIZE 100
typedef int element;
typedef struct {
	element queue[MAX_QUEUE_SIZE];
	int front, rear;
}QueueType;

void error(char *message);
void init(QueueType *q);
int is_empty(QueueType *q);
int is_full(QueueType *q);
void enqueue(QueueType *q, element item);
element dequeue(QueueType *q);
element peek(QueueType *q);

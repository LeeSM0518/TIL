# 정렬 알고리즘

## 01. 선택 정렬 알고리즘

![1547029095907](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1547029095907.png)



* **코드**

  ```c
  void selection_sort(int list[], int n)
  {
  	int i, j, least, temp;
  	for (i = 0; i < n - 1; i++) {
  		least = i;	// 비교할 위치 값을 최소값으로 지정
  		for (j = i + 1; j < n; j++)
  			if (list[j] < list[least])		// 최소값 보다 작으면
  				least = j;					// 최소값을 수정한다.
  		SWAP(list[i], list[least], temp);	// 값을 서로 교체 
  	}
  }
  ```


<br>

## 02. 삽입 정렬

![1547029429443](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1547029429443.png)

* **코드**

  ```c
  void insertion_sort(int list[], int n)
  {
  	int i, j, key;
  	for (i = 1; i < n; i++) {
  		key = list[i];	// 키 지정
  		for (j = i - 1; j >= 0 && list[j] > key; j--)
  			list[j + 1] = list[j];
  		list[j + 1] = key;
  	}
  }
  ```

<br>

## 03. 버블 정렬

![1547040995597](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1547040995597.png)

* **코드**

  ```c
  void bubble_sort(int list[], int n)
  {
  	int i, j, temp;
  	for (i = n - 1; i > 0; i--) {
  		for (j = 0; j < i; j++)
  			if (list[j] > list[j + 1])
  				SWAP(list[j], list[j + 1], temp);
  	}
  }
  ```

<br>

## 04. 합병 정렬

![1547098892096](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1547098892096.png)

* **코드**

  ```c
  // i는 정렬된 왼쪽 리스트에 대한 인덱스
  // j는 정렬된 오른쪽 리스트에 대한 인덱스
  // k는 정렬된 리스트에 대한 인덱스
  void merge(int list[], int left, int mid, int right)
  {
  	int i, j, k, l;
  	i = left;
  	j = mid + 1;
  	k = left;
  
  	// 분할 정렬된 list의 합병
  	while (i <= mid && j <= right) {
  		if (list[i] <= list[j])
  			sorted[k++] = list[i++];
  		else
  			sorted[k++] = list[j++];
  	}
  
  	if (i > mid)	// 남아 있는 레코드의 일괄 복사
  		for (l = j; l <= right; l++)
  			sorted[k++] = list[l];
  	else			// 남아 있는 레코드의 일괄 복사
  		for (l = i; l <= mid; l++)
  			sorted[k++] = list[l];
  
  	// 배열 sorted[]의 리스트를 배열 list[]로 재복사
  	for (l = left; l <= right; l++)
  		list[l] = sorted[l];
  }
  
  void merge_sort(int list[], int left, int right)
  {
  	int mid;
  	if (left < right) {
  		mid = (left + right) / 2;		// 리스트의 균등 분할
  		merge_sort(list, left, mid);		// 부분 리스트 정렬
  		merge_sort(list, mid + 1, right);	// 부분 리스트 정렬
  		merge(list, left, mid, right);		// 합병
  	}
  }
  ```

<br>

## 05. 퀵 정렬

![1547100774477](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1547100774477.png)

![1547100808693](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\1547100808693.png)

* **코드**

  ```c
  void quick_sort(int list[], int left, int right)
  {
  	if (left < right) {
  		int q = partition(list, left, right);	// 피봇 결정
  		quick_sort(list, left, q - 1);
  		quick_sort(list, q + 1, right);
  	}
  }
  
  int partition(int list[], int left, int right)
  {
  	int pivot, temp;
  	int low, high;
  
  	low = left;
  	high = right + 1;
  	pivot = list[left];
  
  	do {
  		do
  			low++;
  		while (low <= right && list[low] < pivot);
  
  		do
  			high--;
  		while (high >= left && list[high] > pivot);
  
  		if (low < high)
  			SWAP(list[low], list[high], temp);
  
  	} while (low < high);
  
  	SWAP(list[left], list[high], temp);
  
  	return high;
  }
  ```

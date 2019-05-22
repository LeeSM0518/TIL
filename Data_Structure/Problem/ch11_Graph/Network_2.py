# for 문으로 깊이우선 탐색을 활용하여 간선 확인하자
# 1. 연결된 딕셔너리에 i가 있는지 없는지
#

def solution(n, computers):
    answer = n
    i = 0
    j = 0
    linked_list = []
    linked_dict = {}
    while True :
        if i == j :
            j += 1
            continue
        elif computers[i][j] == 1 :
            if i not in linked_dict :     # 연결하는 간선 없으면
                linked_dict[i] = [j]
                if j not in linked_dict : # 연결되는 간선 없으면
                    linked_dict[j] = [i]
                answer -= 1
                j += 1
            elif i in linked_dict :      # 연결하는 간선이 있으면
                if j in linked_dict :    # 연결되는 간선이 있으면
                    if j not in linked_dict[i] :     # 연결하는 간선에 연결된 간선이 없으면
                        if i not in linked_dict[j] :
                            linked_dict[i].append(j)
                            linked_dict[j].append(i)
                            answer -= 1
                            j += 1
                        elif i in linked_dict[j] :
                            j += 1
                    elif j in linked_dict[i] :
                        ''''''
                elif j not in linked_dict :
                    ''''''


    return answer

# n = 3
# computers = [[1, 1, 0], [1, 1, 0], [0, 0, 1]]
# solution(3, computers)
'''
[1,1,0]
[1,1,1]
[0,1,1]
'''

class Heap :
    def __init__(self):
        self.heap_data = [None]

    def insert_heap(self, value):
        i = 0
        if self != None :
            if len(self.heap_data) != 1 :
                i = len(self.heap_data)
                while i != 1 and value < self.heap_data[int(i/2)] :
                    i = int(i/2)
                self.heap_data.insert(i, value)
            else :
                self.heap_data.append(value)

def solution(tickets) :
    answer = []
    icn = Heap()
    others = Heap()
    destination = None
    for values in tickets :
        if values[0] == 'ICN' :
            icn.insert_heap(values)
        else :
            others.insert_heap(values)
    icn.heap_data.remove(None)
    others.heap_data.remove(None)
    while True :
        if icn.heap_data == [] and others.heap_data == [] :
            break
        elif answer == [] :
            start = icn.heap_data[0]
            icn.heap_data.remove(start)
            destination = start[1]
            answer.append(start[0])
        elif destination == 'ICN':
            start = icn.heap_data[0]
            icn.heap_data.remove(start)
            destination = start[1]
            answer.append(start[0])
        for route in others.heap_data :
            if route != None :
                if route[0] == destination :
                    answer.append(route[0])
                    destination = route[1]
                    others.heap_data.remove(route)
    answer.append(destination)
    return answer

tickets = [['ICN', 'SFO'],
           ['ICN', 'ATL'],
           ['SFO', 'ATL'],
          ['ATL', 'ICN'],
            ['ATL','SFO']]
print(solution(tickets))



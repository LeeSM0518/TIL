def solution(budgets, M):
    answer = 0
    budgets.sort()
    cut_line = M / len(budgets)
    for i in range(len(budgets)) :
        if budgets[i] > cut_line :
            break
    budget_excess = len(budgets) - i
    for j in range(i) :
        M -= budgets[j]
    answer = int(M / budget_excess)
    for i in range(budget_excess, len(budgets)) :
        budgets[i] = answer
    return answer
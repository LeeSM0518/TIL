import Python_project

ch_kms = 0
ch_menu = 0
ch_choice = 0

KBS = Python_project.Cable()
KBS.CableMake()

MBC = Python_project.Cable()
MBC.CableMake()

SBS = Python_project.Cable()
SBS.CableMake()

while True :
    ch_kms = int(Python_project.print_kms(ch_kms))
    if ch_kms == 1 :
        while True :
            ch_menu = int(Python_project.print_menu(ch_menu))
            if ch_menu == 1 :
                KBS = Python_project.Cable_Plus(KBS)
            elif ch_menu == 2 :
                ch_choice = int(input('시간별(1), 요일별(2) : '))
                if ch_choice == 1 :
                    Python_project.check_time(KBS)
                if ch_choice == 2 :
                    Python_project.check_days(KBS)
            elif ch_menu == 3 :
                Python_project.check_search(KBS)
            elif ch_menu == 4 :
                KBS = Python_project.check_adjust(KBS)
            elif ch_menu == 5 :
                KBS = Python_project.check_delete(KBS)
            elif ch_menu == 6 :
                break
            else :
                print('다시 입력해주세요 : ')
    if ch_kms == 2 :
        while True :
            ch_menu = int(Python_project.print_menu(ch_menu))
            if ch_menu == 1 :
                MBC = int(Python_project.Cable_Plus(MBC))
            elif ch_menu == 2 :
                ch_choice = int(input('시간별(1), 요일별(2) : '))
                if ch_choice == 1 :
                    Python_project.check_time(MBC)
                if ch_choice == 2 :
                    Python_project.check_days(MBC)
            elif ch_menu == 3 :
                Python_project.check_search(MBC)
            elif ch_menu == 4 :
                MBC = Python_project.check_adjust(MBC)
            elif ch_menu == 5 :
                MBC = Python_project.check_delete(MBC)
            elif ch_menu == 6 :
                break
            else :
                print('다시 입력해주세요 : ')
    if ch_kms == 3 :
        while True :
            ch_menu = int(Python_project.print_menu(ch_menu))
            if ch_menu == 1 :
                SBS = int(Python_project.Cable_Plus(SBS))
            elif ch_menu == 2 :
                ch_choice = int(input('시간별(1), 요일별(2) : '))
                if ch_choice == 1 :
                    Python_project.check_time(SBS)
                if ch_choice == 2 :
                    Python_project.check_days(SBS)
            elif ch_menu == 3 :
                Python_project.check_search(SBS)
            elif ch_menu == 4 :
                SBS = Python_project.check_adjust(SBS)
            elif ch_menu == 5 :
                SBS = Python_project.check_delete(SBS)
            elif ch_menu == 6 :
                break
            else :
                print('다시 입력해주세요 : ')






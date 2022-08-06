#프로그램 창을 생성하는 역할을 담당

from map import *
from PyQt5.QtWidgets import QWidget, QApplication, QMessageBox

class CWidget(QWidget): #Qwidget에서 상속받은 클래스
    endSignal = pyqtSignal() #게임종료 시그널을 클래스변수로 생성 (객체변수X)
    #시그널 생성
    def __init__(self): 
        super().__init__()
        self.initUI()
    
    def __del__(self):
        pass
    
    def initUI(self):
        self.setGeometry(100, 100, 500, 500)
        self.setWindowTitle('Snake Game')
        self.setFixedSize(self.rect().size())
        
        self.map = CMap(self) #핵심클래스인 CMap 클래스 변수를 선언하는 코드
        self.endSignal.connect(self.ExitGame) 
        
    def paintEvent(self, e): #QWidget클래스의 함수 오버라이딩
        qp = QPainter() #QPainter 클래스의 변수인 qp를 선언, CMap클래스의 draw()함수로 전달인자를 넘김으로 그리기 권한을 위임하는 형식
        qp.begin(self)
        self.map.draw(qp)
        qp.end()
    
    def keyPressEvent(self, e): #QWidget 클래스의 함수오버라이딩, 키보드를 누르면 그 처리를 CMap의 Keydown()함수를 호출해 위임
        self.map.keydown(e.key())
        
    def ExitGame(self): #CMap에서 게임종료 조건이 충족되면 CWidget으로 보내는 시그널과 연결된 슬롯함수
        result = QMessageBox.information(self
                                         ,'게임종료'
                                         ,'다시시작: Yes, 종료: No'
                                         ,QMessageBox.Yes | QMessageBox.No)
        if result == QMessageBox.Yes:
            self.map.reStart()
        else:
            self.close()
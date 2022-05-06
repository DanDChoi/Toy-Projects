# 뱀의 마디 클래스(CNode)와 이를 조합한 뱀 클래스(CSnake)로 구성

from PyQt5.QtCore import Qt #뱀이동 키보드 이벤트 처리를 위해 PyQt5 모듈을 불러옴

class CNode: 
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y
#CNode 클래스는 뱀의 한 마디를 의미하는 클래스, 맵의 2차원 배열에서 마디의 위치를 저장하는 정수형 변수 x, y (배열의 인덱스)를 가짐.
        
    def __eq__(self, other):
        if self.x == other.x and self.y == other.y:
            return True
        else:
            return False
#CNode 클래스의 같음을 비교하는 __eq__ 연산자 정의, 차후 뱀의 머리 마디가 먹이와 겹쳤는지 (먹이를 먹었는지) 비교하는 용도로 사용할 계획.
        
        
class CSnake: #뱀에 대한 클래스
    def __init__(self, lines): 
        self.node = [] #뱀 마디길이 저장할 리스트
        self.dir = Qt.Key_Right #뱀의 방향
        self.bAdd = False #뱀 마디 추가
        self.init(lines)
    
    def init(self, lines): # 파라미터로 맵의 크기를 받아와서 /2를 통해 맵의 중간에 뱀을 생성
        cx = lines//2
        cy = lines//2
        for i in range(3): #기본 뱀 3마디 생성
            self.node.append(CNode(cx, cy))
            cx -= 1
            
    def changeDir(self, key): #뱀의 이동방향을 변경
        if self.isChangeDir(key) == False:
            return None
        
        self.dir = key
    
    def isChangeDir(self, key): #현대 방향과 이동할 방향이 반대인지?
        if self.dir == Qt.Key_Left and key == Qt.Key_Right:
            return False
        elif self.dir == Qt.Key_Right and key == Qt.Key_Left:
            return False
        elif self.dir == Qt.Key_Up and key == Qt.Key_Down:
            return False
        elif self.dir == Qt.Key_Down and key == Qt.Key_Up:
            return False
        else:
            return True
        
    def isCrash(self): 
        if self.nodeCount() < 5: #구조상 뱀의 마디가 5개 이상시에만 충돌이 가능하므로, 뱀의 마디가 5보다 작다면 검사를 하지 않음
            return False
        
        head = CNode(self.node[0].x, self.node[0].y) #뱀 머리
        bodylist = self.node[4:] 
        
        for body in bodylist:
            if head == body: #머리가 몸통에 닿았다면, True를 리턴
                return True
            
        return False
        
    def move(self):
        if self.isCrash(): #뱀 머리, 몸통 충돌 검사
            return False 
        
        head = CNode(self.node[0].x, self.node[0].y) #뱀 머리
        
        #뱀을 이동시킬때는 모든 마디를 움직인는것이 아니라 '머리와 꼬리 2개의 마디만 변경'하여 이동한다
        #뱀의 현재 머리 좌표(x, y)를 구한후, 왼쪽으로 이동할 경우, x좌표만 1감소시켜 뱀의 머리를 기존의 뱀 리스트에 머리로 추가하고,
        #먹이를 먹은상태라면 꼬리는 그대로, 먹지 않은 상태라면 꼬리를 pop() 함수를 이용, 삭제시켜서 이동한다
        
        if self.dir == Qt.Key_Left: 
            head.x -= 1
        elif self.dir == Qt.Key_Right:
            head.x += 1
        elif self.dir == Qt.Key_Up:
            head.y -= 1
        elif self.dir == Qt.Key_Down:
            head.y += 1
        
        self.node.insert(0, head) #이동방향으로 뱀 머리 추가
        
        if self.bAdd:  #이동시 먹이를 먹었으면 꼬리 유지, 아니면 제거
            self.bAdd = False
        else:
            self.node.pop()
            
        return True
    
    def addNode(self):
        self.bAdd = True
        
    def nodeCount(self):    # 뱀 길이 얻기
        return len(self.node)
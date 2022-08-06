#앱을 시작시키는 메인 파일

import sys
from window import *

QApplication.setAttribute(Qt.AA_EnableHighDpiScaling, True)

if __name__ == '__main__':
    app = QApplication(sys.argv) #QWidget을 생성하기 위해 반드시 선행되어야하는 부분이 QApplication을 만드는 일
    w = CWidget()
    w.show()
    sys.exit(app.exec_())
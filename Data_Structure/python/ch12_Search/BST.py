class BinSearchTree :
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.tree = {'RootNode' : None, 'LeftChild' : None, 'RightChild' : None}

    def searchBST(self, key):
        if(self == None):
            return None

        pReturn = self.tree['RootNode']
        while pReturn != None :
            if key == pReturn.key :
                break
            elif key < pReturn.key :
                pReturn = pReturn.tree['LeftChild']
            else :
                pReturn = pReturn.tree['RightChild']

        return pReturn

    def getParentNode(self, key, ppResult):
        ret = 1
        pParentNode = None
        ppResult = 0

        while self != None :
            if key == self.key :
                print('오류, 중복된 키 - [{}], getParentNode()'.format(key))
                ret = 0
                return ret
            elif key < self.key :
                pParentNode = self
                self = self.tree['LeftChild']
            else :
                pParentNode = self
                self = self.tree['RightChild']

        if 1 == ret :
            ppResult = pParentNode

        return ppResult

    def insertNewBinSearchTreeNode(self, ParentNode, NewNode):
        if ParentNode == None :
            self.tree['RootNode'] = NewNode
        else :
            if NewNode.key < ParentNode.key :
                ParentNode.tree['LeftChild'] = NewNode
            else :
                ParentNode.tree['RightChild'] = NewNode

    def insertDataBST(self, key, value):
        ret = 0
        ParentNode = BinSearchTree(None, None)
        if self != None :
            ret = ParentNode.getParentNode(self.tree['RootNode'], key, ParentNode )
        if ret == 0 :
            return ret
        NewNode = BinSearchTree(key, value)

        self.insertNewBinSearchTreeNode(self, ParentNode, NewNode)

    def searchWithParentNodeBST(self, key, ParentNode):
        ''''''

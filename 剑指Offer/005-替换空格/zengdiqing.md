**思路：**

因为一个空格要替换成三个字符（%20），所以当遍历到一个空格时，需要在尾部填充两个任意字符。令p1指向字符串原来的末尾位置，p2指向字符串现在的末尾位置。
p1和p2从后向前遍历，当p1遍历到一个空格时，就需要令p2指向的位置依次填充02%，否则就填充上p1指向字符的值。

```py
class Solution:
  def replaceSpace(self, s):
    if not isinstance(s,str) or len(s) <= 0 or s == None:
      return ''
    spaceNum = 0
    for i in s:
      if i == "":
        spaceNum += 1
    newStrLen = len(s) + spaceNum*2
    newStr = newStrLen*[None]
    indexOfOriginal, indexOfNew = len(s) - 1, newStrLen - 1
    while indexOfNew >= 0 and indexOfOriginal <= indexOfNew:
      if s[indexOfOriginal] == '':
        newStr[indexOfNew-2:indexOfNew+1] = ['%','2','0']
        indexOfNew -= 3
        indexOfOriginal -= 1
      else:
        newStr[indexOfNew] = s[indexOfOriginal]
        indexOfNew -= 1
        indexOfOriginal -= 1
    return ''.join(newStr)
  
```
时间复杂度：O(n)

空间复杂度：O(n)

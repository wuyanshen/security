# 开发踩坑记
## powerdesigner使用
   1.[powerdesigner连接mysql数据库配置](https://www.cnblogs.com/deng-cc/p/6824946.html)
   
   2.[物理数据模型导入mysql数据库](https://jingyan.baidu.com/article/3ea51489aea1f652e61bbacf.html)
   
   3.[物理数据模型导出设置](https://blog.csdn.net/leon90dm/article/details/8142737)
   
## log4j使用
   1.[ase initialize the log4j system properly解决办法](http://hehongwei44.iteye.com/blog/1494999)
   
   2.[log4j的ConversionPattern参数的格式含义](http://www.blogjava.net/wilesun/archive/2007/10/30/156999.html)
   
   3.[log4j.properties配置详解](https://www.cnblogs.com/ITEagle/archive/2010/04/23/1718365.html)
## git使用
   [如何在IntelliJ IDEA中使用.ignore插件忽略不必要提交的文件](https://blog.csdn.net/qq_34590097/article/details/56284935)
   
    输入： 
    git rm -r –cached filePath 
    git commit -m “remove xx” 
    或者： 
    git rm -r –-cached . 
    git add . 
    git commit -m “update .gitignore”
    来解释下几个参数 -r 是删除文件夹及其子目录 –cached 是删除暂存区里的文件而不删除工作区里的文件，第一种是删除某个文件，第二种方法就把所有暂存区里的文件删了，再加一遍，相当于更新了一遍。
   
## 验证码使用
  [JAVA 验证码生成工具类及使用](https://blog.csdn.net/hgyu_962464/article/details/60586941)
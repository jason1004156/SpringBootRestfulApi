# SpringBootRestfulApi
***
# 建置
 該專案有使用lombok https://projectlombok.org/download
 
 請先安裝相關jar檔於您的IDE
#Docker執行
 若欲以Docker執行，請先執行**build.bat**後運行**enableDocker.bat**
***
 基於Spring Boot所實作的Restful Api
 
 該專案索回傳api統一格式皆由`"state"`與`"data"`所組成
 
 當 `"state" : "200"`
 
 代表操作成功，`"data"`內會顯示回傳的資料
 
 當 `"state" : "500"`
 
 代表操作失敗，`"data"`內會顯示錯誤訊息
 
 # 回傳範例
 ```JSON{
    "state": "200",
    "data": [
        {
            "name": "Spring 2.0技術手冊",
            "translator": null,
            "price": 0.0,
            "author": "林信良",
            "publisher": "電子工業出版社",
            "publicationDate": "2008-11-01",
            "isbn": "986-18-1106-0"
        },
        {
            "name": "我寫的書",
            "translator": "扛義",
            "price": 0.0,
            "author": "扛",
            "publisher": "電子工業出版社",
            "publicationDate": "2022-04-07",
            "isbn": "000-18-1803-1"
        }
    ]
}
```
 
# API路徑
***
## Book物件
```JSON{
            "name": "Spring 2.0技術手冊",
            "translator": null,
            "price": 0.0,
            "author": "林信良",
            "publisher": "電子工業出版社",
            "publicationDate": "2008-11-01",
            "isbn": "986-18-1106-0"
        }
  ```
        
 ## 查詢全部或依參數查詢
  GET localhost:8080/book

  ### 支援參數
  以下參數為**Case Sensitve**
  |參數名稱|類型|
  | :-----|:---|
  |ISBN|字串|
  |Name|字串|
  |Author|字串|
  |Translator|字串|
  |Publisher|字串|
  |PublicationDate|日期字串，yyyy-MM-dd|
  |PublicationDateStart|日期字串，yyyy-MM-dd|
  |PublicationDateEnd|日期字串，yyyy-MM-dd|
  |Price|數字(Double)|
  
  回傳Book清單
 ## 依ISBN查詢
  GET localhost:8080/book/{ISBN}
  
  回傳Book物件
 ## 儲存
  POST localhost:8080/book/save
  
  無回傳
 ## 更新
  PUT localhost:8080/book/{ISBN}
  
  JSON放置欲更新的Book物件
  
  此API會將呼叫路徑上的ISBN更新成JSON中的Book物件
  
  無回傳
 ## 刪除
  DELETE localhost:8080/book/{ISBN}
  
  依ISBN刪除物件
  
  無回傳

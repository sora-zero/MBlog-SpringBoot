# microblog overview

## 功能

用户登陆与注册，blog发表和删除,编辑，follow and unfollow other users

微博时间线，自己发表的微博按照时间线排序，按照时间线获取关注对象的微博

## 页面
1. 登录页面
2. 注册页面
3. 主页面
4. 用户页面

## 主要功能流程描述
1. 用户登录
2. 发表微博
3. 删除微博

## 数据

mysql中的数据：
两张表blog和user

blog:
blog\_id
user\_id
publish\_time
content

user:
userid
usernmae
avatarid
md5password

redis中的数据结构：
每个用户：
* sorted set 自己发布的微博id按时间排序
* sorted set 关注对象的微博id按时间排序
* set 保存自己关注的对象
* set 保存follower的id


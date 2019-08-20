# FastDFS

## Install
libfastcommon
```shell
git clone https://github.com/happyfish100/libfastcommon.git
cd libfastcommon
./make.sh
sudo ./make.sh install
```

FastDFS
```shell
## download FastDFS from https://sourceforge.net/projects/fastdfs/
tar xzf FastDFS_v5.x.tar.gz
cd FastDFS
./make.sh
sudo ./make.sh install
```

## Configuration

```shell
cd /etc/fdfs/
mv tracker.conf.sample tracker.conf
mv storage.conf.sample storage.conf
mv client.conf.sample client.conf

## 修改base_path，指定存放的地址
vim tracker.conf
base_path=xxx

## 修改tracker_server
vim storage.comf
tracker_server=xxx

vim client.conf
tracker_server=xxx
```

## Run

```shell
## run tracker server
/usr/bin/fdfs_trackerd /etc/fdfs/tracker.conf restart

## run storage server
/usr/bin/fdfs_storaged /etc/fdfs/storage.conf restart

## run client and upload
/usr/bin/fdfs_test <client_conf> upload <local_file>
/usr/bin/fdfs_test1 <client_conf> upload <local_file>
```

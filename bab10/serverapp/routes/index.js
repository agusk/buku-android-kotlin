"use strict";

var express = require('express');
var router = express.Router();

var sqlite3 = require('sqlite3').verbose();
var db = new sqlite3.Database(':memory:');

db.run("CREATE TABLE IF NOT EXISTS " + 
    "pegawai (id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
    "nip TEXT NOT NULL, nama TEXT NOT NULL, email TEXT NOT NULL, " + 
    "jeniskelamin TEXT,umur INTEGER)");

router.get('/', function(req, res, next) {
  res.json({
    message: 'Selamat datang di server API'
  })
  
});

router.get('/all', function(req, res, next) {
  db.all("SELECT id,nip,nama,email,jeniskelamin,umur from pegawai", function(err, rows) {    
    res.json(rows)
  });
  
});
router.post('/insert', function(req, res, next) {
  var nip = req.body.nip;
  var nama = req.body.nama;
  var email = req.body.email;
  var jeniskelamin = req.body.jeniskelamin;
  var umur = req.body.umur;

  var sql = "insert into pegawai(nama,email,jeniskelamin,umur,nip) values(?,?,?,?,?)";
  var params = [nama,email,jeniskelamin,umur,nip,];
  db.run(sql,params, function(err) {  
    if(!err)  {
      res.json({message:"sukses tambah data", id: this.lastID});
    }else{
      res.json({message:"gagal tambah data"});
    }
    
  });
  
});
router.post('/update', function(req, res, next) {
  var id = req.body.id;
  var nip = req.body.nip;
  var nama = req.body.nama;
  var email = req.body.email;
  var jeniskelamin = req.body.jeniskelamin;
  var umur = req.body.umur;

  var sql = "update pegawai set nama=?,email=?,jeniskelamin=?,umur=?,nip=? where id=?";
  var params = [nama,email,jeniskelamin,umur,nip,id,];
  db.run(sql,params, function(err) {  
    if(!err)  {
      res.json({message:"sukses edit data"});
    }else{
      res.json({message:"gagal edit data"});
    }
    
  });
  
});
router.post('/delete', function(req, res, next) {
  var id = req.body.id;

  var sql = "delete from pegawai where id=?";
  var params = [id,];
  db.run(sql,params, function(err) {  
    if(!err)  {
      res.json({message:"sukses hapus data"});
    }else{
      res.json({message:"gagal hapus data"});
    }
    
  });
  
});

module.exports = router;

package com.zsy.domain;

import lombok.Data;

@Data
public class User {
  private int userId;
  private String userName;
  private int avatarId;
  private String md5Password;

}

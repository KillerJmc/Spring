package com.jmc.dao;

import com.jmc.domain.Admin;

import java.util.List;

public interface AdminDao {
    Admin getByName(String name);
}

package com.gzho.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gzho.entity.Category;
import com.gzho.service.CategoryService;
import com.gzho.service.impl.BaseServiceImpl;
import com.td.util.page.Page;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
}

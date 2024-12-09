package com.kaiyu.common.domain;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kaiyu.common.utils.BeanUtils;
import com.kaiyu.common.utils.CollUtils;
import com.kaiyu.common.utils.Convert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Data
@NoArgsConstructor
@Schema(description = "分页结果")
public class PageR<T> {

    private int code = 200;

    @Schema(description = "总条数")
    protected Long total;
    @Schema(description = "总页码数")
    protected Long pages;
    @Schema(description = "当前页数据")
    protected List<T> list;

    public PageR(Long total, Long pages, List<T> list) {
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

    public static <T> PageR<T> empty(Long total, Long pages) {
        return new PageR<>(total, pages, CollUtils.emptyList());
    }
    public static <T> PageR<T> empty(Page<?> page) {
        return new PageR<>(page.getTotal(), page.getPages(), CollUtils.emptyList());
    }

    public static <T> PageR<T> of(Page<T> page) {
        if(page == null){
            return new PageR<>();
        }
        if (CollUtils.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageR<>(page.getTotal(), page.getPages(), page.getRecords());
    }
    public static <T,R> PageR<T> of(Page<R> page, Function<R, T> mapper) {
        if(page == null){
            return new PageR<>();
        }
        if (CollUtils.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageR<>(page.getTotal(), page.getPages(),
                page.getRecords().stream().map(mapper).collect(Collectors.toList()));
    }

    public static <T> PageR<T> of(Long total, List<T> list) {
        return new PageR<>(total, 0L, list);
    }

    public static <T> PageR<T> of(Page<?> page, List<T> list) {
        return new PageR<>(page.getTotal(), page.getPages(), list);
    }

    public static <T, R> PageR<T> of(Page<R> page, Class<T> clazz) {
        return new PageR<>(page.getTotal(), page.getPages(), BeanUtils.copyList(page.getRecords(), clazz));
    }

    public static <T, R> PageR<T> of(Page<R> page, Class<T> clazz, Convert<R, T> convert) {
        return new PageR<>(page.getTotal(), page.getPages(), BeanUtils.copyList(page.getRecords(), clazz, convert));
    }

    @Schema(accessMode = READ_ONLY)
    @JsonIgnore
    public boolean isEmpty(){
        return list == null || list.size() == 0;
    }
}

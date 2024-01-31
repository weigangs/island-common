package com.lkyl.island.common.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuComponentEnum {
    /** Layout组件标识 */
    LAYOUT("Layout", "Layout组件标识"),
    /** ParentView组件标记 */
    PARENT_VIEW("ParentView", "ParentView组件标记"),
    /** InnerLink组件标识 */
    INNER_LINK("InnerLink", "InnerLink组件标记");


    private String code;

    private String msg;

}

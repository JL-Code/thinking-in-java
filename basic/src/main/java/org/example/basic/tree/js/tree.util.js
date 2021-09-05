// 转化为线性结构
export function flatten(nodes) {
    let result = [];
    let nexts = [].concat(nodes);
    for (var i = 0; i < nexts.length; i++) {
        let node = nexts[i];
        result.push(node);
        if (Array.isArray(node.children)) {
            nexts = nexts.concat(node.children);
            node.children = null;
        }
    }
    return result;
}

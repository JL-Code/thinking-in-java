import data from "./data.json";
import {flatten} from "./tree.util";

let nodes = flatten(data);
let serialNumber = 0;
nodes
    .sort((m) => m.subjectCode)
    .map((m) => {
        serialNumber++;
        m.serialNumber = serialNumber;
        return m;
    });

function buildTree(nodes) {
    let result = [];
    let roots = nodes.filter((n) => !n.parentCode);

    roots.forEach((root) => {
        _buildSubTree(root, nodes);
        result.push(root);
    });

    return result;
}

function _buildSubTree(node, nodes) {
    let children = nodes
        .filter((n) => n.parentCode === node.subjectCode)
        .sort((m) => m.subjectCode);

    if (!Array.isArray(children)) {
        return;
    }

    children.forEach((child) => {
        _buildSubTree(child, nodes);
    });

    node.children = [...children];
}

let result = buildTree(nodes);

console.log("result:", result);

# CAP 定理

## 概述

![img](https://img2018.cnblogs.com/blog/285763/201906/285763-20190621144256061-464757033.png)

CAP 定理，它指出在一个分布式系统中，不可能同时满足以下三点：

1. 一致性（**C**onsistency） （等同于所有节点访问同一份最新的数据副本）
2. [可用性](https://zh.wikipedia.org/wiki/可用性)（**A**vailability）（每次请求都能获取到非错的响应——但是不保证获取的数据为最新数据）
3. [分区容错性](https://zh.wikipedia.org/w/index.php?title=网络分区&action=edit&redlink=1)（**P**artition tolerance）（以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择[[3\]](https://zh.wikipedia.org/wiki/CAP定理#cite_note-3)。）

根据定理，分布式系统只能满足三项中的两项，而不可能完全满足三项。

## Partition tolerance 的前提下  Consistency 和 Availability 的矛盾

> 理解CAP理论的最简单方式是想象两个节点分处分区两侧。允许至少一个节点更新状态会导致数据不一致，即丧失了C性质。如果为了保证数据一致性，将分区一侧的节点设置为不可用，那么又丧失了A性质。除非两个节点可以互相通信，才能既保证C又保证A，这又会导致丧失P性质。<font size=2>[来自维基百科](https://zh.wikipedia.org/wiki/CAP%E5%AE%9A%E7%90%86)</font>

受限于人为因素和自然因素，分区容错性是无法满足的，因为可能存在自然灾害破坏、人为操作失误等因素导致网络通信中断，从而出现网络分区 ，一旦出现网络分区，如果要保证 **Availability** 则一定会牺牲 **Consistency**，保证 **Consistency** 则一定会牺牲 **Availability**。

## 参考来源

* [CAP原则(CAP定理)、BASE理论](https://www.cnblogs.com/duanxz/p/5229352.html)


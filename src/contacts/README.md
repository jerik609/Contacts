TODOs:
- serialize and deserialize create and modify date
- add serialization uiid
- navigable menu
- searching via anything - build searchable dictionary with key return to display the actual item, well, return a pair of <class,key>

IDEAS:
1. when selecting a list, just return: a list of <key, type, display value> triples (tuples), use some numbering on FE & key on the BE
2. for menu, use a double linked listed or a tree to navigate down and up
3. for searching, just add a "flat" "to string" on keyed, regex the string, then return a list of <type, key>s and display the list as the found ones

OK, number 1 is irrelevant, we're passing references, it does not cost that much really.
Implements the command pattern.

Menu = client
Contacts = receiver
??? = invoker

======================

menu vs commands -> menus are tree structures, while commands are flat ... we navigate the tree

until there's a terminal operation, and then we return a command which we execute

SelectAction -> SelectEntity -> EnterPerson
                             -> EnterOrganization

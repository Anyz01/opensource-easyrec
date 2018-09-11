<%--
  ~ Copyright 2011 Research Studios Austria Forschungsgesellschaft mBH
  ~
  ~ This file is part of easyrec.
  ~
  ~ easyrec is free software: you can redistribute it and/or modify
  ~ it under the terms of the GNU General Public License as published by
  ~ the Free Software Foundation, either version 3 of the License, or
  ~ (at your option) any later version.
  ~
  ~ easyrec is distributed in the hope that it will be useful,
  ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~ GNU General Public License for more details.
  ~
  ~ You should have received a copy of the GNU General Public License
  ~ along with easyrec.  If not, see <http://www.gnu.org/licenses/>.
  --%>

<div id="clusterManagerIndex" style="height:100%;">
    <div id="clusterManagerTreeView" style="height:100%;">
        <jsp:include page="treeview.jsp"/>
    </div>
    <div id="clusterManagerViewItems">
        <jsp:include page="help.jsp"/>
    </div>
    <div id="clusterManagerSearchItems">
        <jsp:include page="searchitems.jsp"/>
    </div>
</div>

<script type="text/javascript">
    manageResize();    // initialize the sizes of all UI components
</script>
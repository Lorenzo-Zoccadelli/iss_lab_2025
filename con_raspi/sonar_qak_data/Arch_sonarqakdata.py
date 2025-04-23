### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('sonarqakdataArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctxsonar', graph_attr=nodeattr):
          datacleaner=Custom('datacleaner','./qakicons/symActorWithobjSmall.png')
          sonarsender=Custom('sonarsender','./qakicons/symActorWithobjSmall.png')
          sonarreciever=Custom('sonarreciever','./qakicons/symActorWithobjSmall.png')
     datacleaner >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sonarsender
     sonarsender >> Edge( label='cleaneddata', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     sonarsender >> Edge( label='cleaneddata', **eventedgeattr, decorate='true', fontcolor='red') >> sonarreciever
diag

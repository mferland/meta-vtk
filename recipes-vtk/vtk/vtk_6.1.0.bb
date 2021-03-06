require vtk.inc

DEPENDS = "virtual/libx11 virtual/libgl libxt expat freetype jpeg libxml2 libpng zlib tiff vtk-native hdf5 libogg libtheora netcdf3-cxx"
RDEPENDS_${PN} = "xserver-xorg-extension-glx"

SRC_URI += "file://web_app_compile_fix.patch \
file://remove_qt_designer_plugin.patch \
file://site-file.cmake \
file://fix_gcc6_compiling.patch \
file://fix_glintptr_error.patch \
file://vtk_freetype_compile_fix.patch \
"

SRC_URI[md5sum] = "25e4dfb3bad778722dcaec80cd5dab7d"
SRC_URI[sha256sum] = "bd7df10a479606d529a8b71f466c44a2bdd11fd534c62ce0aa44fad91883fa34"

inherit qt4x11 cmake python-dir pythonnative

PYTHON_WRAPPER_DEPENDS = "python python-native"
PYTHON_WRAPPER_RDEPENDS = "python-twisted python-autobahn python-zopeinterface"
PYTHON_WRAPPER_CONF = "-DVTK_WRAP_PYTHON:BOOL=ON -DVTK_USE_SYSTEM_TWISTED:BOOL=ON -DVTK_USE_SYSTEM_AUTOBAHN:BOOL=ON -DVTK_USE_SYSTEM_ZOPE:BOOL=ON"

PACKAGECONFIG ??= "GroupImaging GroupQt GroupRendering GroupViews"
PACKAGECONFIG[GroupImaging] = "-DVTK_Group_Imaging:BOOL=ON,-DVTK_Group_Imaging:BOOL=OFF"
PACKAGECONFIG[GroupQt] = "-DVTK_Group_Qt:BOOL=ON,-DVTK_Group_Qt:BOOL=OFF"
PACKAGECONFIG[GroupRendering] = "-DVTK_Group_Rendering:BOOL=ON,-DVTK_Group_Rendering:BOOL=OFF"
PACKAGECONFIG[GroupViews] = "-DVTK_Group_Views:BOOL=ON,-DVTK_Group_Views:BOOL=OFF"
PACKAGECONFIG[GroupWeb] = "-DVTK_Group_Web:BOOL=ON ${PYTHON_WRAPPER_CONF},-DVTK_Group_Web:BOOL=OFF,${PYTHON_WRAPPER_DEPENDS},${PYTHON_WRAPPER_RDEPENDS}"

# Support for openmpi not available yet
# PACKAGECONFIG[GroupMPI] = "-DVTK_Group_MPI:BOOL=ON,-DVTK_Group_MPI:BOOL=OFF"

EXTRA_OECMAKE = "-DBUILD_DOCUMENTATION:BOOL=OFF \
-DBUILD_EXAMPLES:BOOL=OFF \
-DBUILD_SHARED_LIBS:BOOL=ON \
-DBUILD_TESTING:BOOL=OFF \
-DCMAKE_BUILD_TYPE:STRING=Release \
-DVTK_USE_SYSTEM_FREETYPE:BOOL=ON \
-DVTK_USE_SYSTEM_JPEG:BOOL=ON \
-DVTK_USE_SYSTEM_PNG:BOOL=ON \
-DVTK_USE_SYSTEM_TIFF:BOOL=ON \
-DVTK_USE_SYSTEM_ZLIB:BOOL=ON \
-DVTK_USE_SYSTEM_LIBXML2:BOOL=ON \
-DVTK_USE_SYSTEM_EXPAT:BOOL=ON \
-DVTK_USE_SYSTEM_HDF5:BOOL=ON \
-DVTK_USE_SYSTEM_NETCDF:BOOL=ON \
-DVTK_USE_SYSTEM_OGGTHEORA:BOOL=ON \
-DOGGTHEORA_NO_444_SUBSAMPLING:STRING=0 \
-DVTK_USE_X:BOOL=ON \
-DVTK_WRAP_JAVA:BOOL=OFF \
-DVTK_WRAP_TCL:BOOL=OFF \
-DVTKCompileTools_DIR=${STAGING_LIBDIR_NATIVE}/cmake/vtk-${MAJ_VER} \
"

PACKAGES =+ "${PN}-web-demo python-${PN} python-${PN}-dbg"

ALLOW_EMPTY_${PN}-web-demo = "1"
ALLOW_EMPTY_python-${PN} = "1"

FILES_${PN}-web-demo = "${libdir}/www"
FILES_python-${PN}-dbg += "${bindir}/.debug/vtkpython ${PYTHON_SITEPACKAGES_DIR}/${PN}/.debug/*.so"
FILES_python-${PN} = "${bindir}/vtkpython ${PYTHON_SITEPACKAGES_DIR}/${PN}/*"
FILES_${PN}-dev += "${libdir}/cmake ${datadir}/vtk-6.1"

# TODO:
# - look at CMakeCache.txt for VTK_USE_SYSTEM stuff
# //Use system-installed JsonCpp
# VTK_USE_SYSTEM_JSONCPP:BOOL=OFF
# //Use system-installed LIBPROJ4
# VTK_USE_SYSTEM_LIBPROJ4:BOOL=OFF # broken?
# - add a VTK_CONFIG_FLAGS usefull to activate certain features (python wrapping,etc.)

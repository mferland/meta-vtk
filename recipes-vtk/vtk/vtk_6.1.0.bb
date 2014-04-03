DESCRIPTION = "The Visualisation Toolkit"
HOMEPAGE = "http://www.vtk.org"
SECTION = "libs"
LICENSE = "license_vtk"
LIC_FILES_CHKSUM = "file://Copyright.txt;md5=71c9a8e9f868215797781c5bd5b254b8"

DEPENDS = "virtual/libx11 virtual/libgl libxt expat freetype jpeg libxml2 libpng zlib tiff vtk-native"
RDEPENDS_${PN} = "xserver-xorg-extension-glx"

PVMAJOR = "6.1"

SRC_URI = "http://www.vtk.org/files/release/${PVMAJOR}/VTK-${PV}.tar.gz \
file://site-file.cmake \
file://web_app_compile_fix.patch \
file://remove_qt_designer_plugin.patch \
"

S = "${WORKDIR}/VTK-${PV}"

PR = "r1"

SRC_URI[md5sum] = "25e4dfb3bad778722dcaec80cd5dab7d"
SRC_URI[sha256sum] = "bd7df10a479606d529a8b71f466c44a2bdd11fd534c62ce0aa44fad91883fa34"

inherit qt4x11 cmake python-dir pythonnative

PACKAGECONFIG ??= "GroupImaging GroupQt GroupRendering GroupViews GroupWeb"
PACKAGECONFIG[GroupImaging] = "-DVTK_Group_Imaging:BOOL=ON,-DVTK_Group_Imaging:BOOL=OFF"
PACKAGECONFIG[GroupQt] = "-DVTK_Group_Qt:BOOL=ON,-DVTK_Group_Qt:BOOL=OFF"
PACKAGECONFIG[GroupRendering] = "-DVTK_Group_Rendering:BOOL=ON,-DVTK_Group_Rendering:BOOL=OFF"
PACKAGECONFIG[GroupViews] = "-DVTK_Group_Views:BOOL=ON,-DVTK_Group_Views:BOOL=OFF"
PACKAGECONFIG[GroupWeb] = "-DVTK_Group_Web:BOOL=ON \
-DVTK_WRAP_PYTHON:BOOL=ON \
-DVTK_USE_SYSTEM_TWISTED:BOOL=ON \
-DVTK_USE_SYSTEM_AUTOBAHN:BOOL=ON \
-DVTK_USE_SYSTEM_ZOPE:BOOL=ON,\
-DVTK_Group_Web:BOOL=OFF,\
python python-native,\
python-twisted python-autobahn python-zopeinterface"

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
-DVTK_USE_X:BOOL=ON \
-DVTK_WRAP_JAVA:BOOL=OFF \
-DVTK_WRAP_TCL:BOOL=OFF \
-DVTKCompileTools_DIR=${STAGING_LIBDIR_NATIVE}/cmake/vtk-${PVMAJOR} \
${EXTRA_OECONF} \
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
# //Use system-installed HDF5
# VTK_USE_SYSTEM_HDF5:BOOL=OFF
# //Use system-installed JsonCpp
# VTK_USE_SYSTEM_JSONCPP:BOOL=OFF
# //Use system-installed LIBPROJ4
# VTK_USE_SYSTEM_LIBPROJ4:BOOL=OFF
# //Use system-installed NetCDF
# VTK_USE_SYSTEM_NETCDF:BOOL=OFF
# //Use system-installed OGGTHEORA
# VTK_USE_SYSTEM_OGGTHEORA:BOOL=OFF
# - add a VTK_CONFIG_FLAGS usefull to activate certain features (python wrapping,etc.)
